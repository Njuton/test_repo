package com.example.myapp.service;

import com.example.myapp.dao.PostDao;
import com.example.myapp.dao.entity.Post;
import com.example.myapp.dao.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PostService {

    private final PostDao postDao;

    public PostService(PostDao postDao) {
        this.postDao = postDao;
    }


    public Post createPost(UUID userId, String text) {
        Post post = new Post();
        User user = new User();
        user.setId(userId);
        post.setUser(user);
        post.setText(text);
        post.setCreatedAt(LocalDateTime.now());
        post.setId(UUID.randomUUID());
        postDao.save(post);
        getPostById(post.getId());
        return post;
    }

    @CachePut(value = "post", key = "#postId")
    public Post updatePost(UUID postId, String text) {
        Post post = getPostById(postId);
        post.setText(text);
        postDao.update(post);
        return post;
    }

    @CacheEvict(value = "post", key = "#postId")
    public void deletePost(UUID postId) {
        postDao.delete(postId);
    }

    @Cacheable(value = "post", key = "#postId")
    public Post getPostById(UUID postId) {
        return postDao.getById(postId);
    }
}
