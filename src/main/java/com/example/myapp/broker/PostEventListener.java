package com.example.myapp.broker;

import com.example.myapp.service.dto.PostDto;
import com.example.myapp.ws.WebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author rmartynov
 * @since 19.05.2025
 */
@Component
public class PostEventListener {

    private static final Logger logger = LoggerFactory.getLogger(PostEventListener.class);

    private final WebSocketService webSocketService;

    public PostEventListener(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @RabbitListener(queues = {"groupQueue_1", "groupQueue_2", "groupQueue_3"})
    public void handlePostEventForGroup1(PostDto postDto) {
        logger.info("Received msg from RabbitMQ: {}", postDto);
        webSocketService.notifyFriends(postDto);
    }
}
