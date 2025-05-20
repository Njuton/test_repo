package com.example.myapp.ws;

import com.example.myapp.service.UserService;
import com.example.myapp.service.dto.PostDto;
import com.example.myapp.service.dto.UserDto;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

/**
 * @author rmartynov
 * @since 18.05.2025
 */
@Controller
public class WebSocketService {
    private final SimpMessagingTemplate messagingTemplate;
    private final UserService userService;

    public WebSocketService(SimpMessagingTemplate simpMessagingTemplate, UserService userService) {
        this.messagingTemplate = simpMessagingTemplate;
        this.userService = userService;
    }

    public void notifyFriends(PostDto postDto) {
        UUID userId = postDto.userId();
        List<UserDto> friends = userService.getFriends(userId);
        for (UserDto friend : friends) {
            // отправка происходит на /user/{sessionId}/post/feed/posted, где sessionId формируется клиентом WebSocket
            // связь sessionId - username поддерживает Spring
            // клиентская сторона должна быть подписана на /user/post/feed/posted
            messagingTemplate.convertAndSendToUser(friend.id().toString(), "/post/feed/posted", postDto
            );
        }
    }
}
