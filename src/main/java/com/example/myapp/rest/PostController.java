package com.example.myapp.rest;

import com.example.myapp.config.RabbitMQConfig;
import com.example.myapp.dao.PostDao;
import com.example.myapp.dao.entity.Post;
import com.example.myapp.service.PostService;
import com.example.myapp.service.UserService;
import com.example.myapp.service.dto.PostDto;
import com.example.myapp.service.dto.UserDto;
import com.example.myapp.utils.tx.TransactionRunner;
import com.example.myapp.utils.tx.TxMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
public class PostController implements PostApi {

    private final PostService postService;
    private final UserService userService;
    private final TransactionRunner txRunner;
    private final PostDao postDao;
    private final RabbitTemplate rabbitTemplate;

    public PostController(PostService postService, UserService userService, TransactionRunner txRunner,
                          PostDao postDao, RabbitTemplate rabbitTemplate) {
        this.postService = postService;
        this.userService = userService;
        this.txRunner = txRunner;
        this.postDao = postDao;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/create")
    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UUID> createPost(Principal principal, String text) {
        UUID userId = UUID.fromString(principal.getName());
        Post post = txRunner.runInTransaction(() -> postService.createPost(userId, text), TxMode.CURRENT_OR_NEW, null);
        PostDto dto = new PostDto(post.getId(), post.getUser().getId(), post.getText(), post.getCreatedAt());
        // Отправка сообщения в RabbitMQ
        String routingKey = determineRoutingKeyForUser(userId.toString());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, routingKey, dto);
        return ResponseEntity.ok(post.getId());
    }

    // раскидываем сообщение по 3 очередям в зависимости от хэш-кода отправителя
    private String determineRoutingKeyForUser(String userId) {
        int groupNumber = (userId.hashCode() % 3) + 1;
        return "post.created.group" + groupNumber;
    }


    @PutMapping("/update")
    @Override
    public ResponseEntity<Void> updatePost(UUID postId, String text) {
        txRunner.runInTransaction(() -> {
            postService.updatePost(postId, text);
        }, TxMode.CURRENT_OR_NEW, null);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<Void> deletePost(UUID id) {
        txRunner.runInTransaction(() -> {
            postService.deletePost(id);
            return null;
        }, TxMode.CURRENT_OR_NEW, null);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{id}")
    @Override
    public ResponseEntity<PostDto> getPost(UUID id) {
        Post post = txRunner.runInTransaction(() -> postService.getPostById(id), TxMode.READ_ONLY, null);
        return ResponseEntity.ok(new PostDto(post.getId(), post.getUser().getId(), post.getText(), post.getCreatedAt()));
    }

    @GetMapping("/feed")
    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<PostDto>> getFriendsFeed(Principal principal, int offset, int limit) {
        UUID userId = UUID.fromString(principal.getName());
        List<UUID> friendIds = txRunner.runInTransaction(() ->
                userService.getFriends(userId).stream().map(UserDto::id).toList(), TxMode.READ_ONLY, null);
        List<UUID> postIds = txRunner.runInTransaction(() -> postDao.getFriendsPostsIds(friendIds, offset, limit), TxMode.READ_ONLY, null);
        List<PostDto> posts = postIds.stream().map(postService::getPostById)
                .map(post -> new PostDto(
                        post.getId(),
                        post.getUser().getId(),
                        post.getText(),
                        post.getCreatedAt())).toList();
        return ResponseEntity.ok(posts);
    }
}