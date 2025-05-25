package com.example.myapp.service;

import com.example.myapp.dao.entity.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.lettuce.core.RedisClient;
import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author rmartynov
 * @since 04.05.25
 */
@Service
@ConditionalOnProperty(name = "redis.enabled", havingValue = "true")
public class RedisDialogService {

    private final RedisClient redisClient;
    private final StatefulRedisConnection<String, String> connection;
    private final RedisCommands<String, String> commands;
    private final ObjectMapper objectMapper;

    public RedisDialogService(@Value("${redis.host}") String host, @Value("${redis.port}") int port) {
        this.redisClient = RedisClient.create("redis://%s:%s".formatted(host, port));
        this.connection = redisClient.connect();
        this.commands = connection.sync();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public void sendMessage(UUID senderId, UUID receiverId, String text) {
        // все сообщения между пользователя хранятся в списке по ключу: sender_id:receiver_id
        String script = "local message = {id=ARGV[1], senderId=ARGV[2], receiverId=ARGV[3], text=ARGV[4], createdAt=ARGV[5]} " +
                "redis.call('RPUSH', KEYS[1], cjson.encode(message))";
        String messageId = UUID.randomUUID().toString();
        String createdAt = LocalDateTime.now().toString();
        commands.eval(script, ScriptOutputType.STATUS, new String[]{getDialogKey(senderId, receiverId)},
                messageId, senderId.toString(), receiverId.toString(), text, createdAt);
    }

    public List<Message> getDialog(UUID userId, UUID friendId) {
        // получить все сообщения списка по ключу: sender_id:receiver_id
        String script = "return redis.call('LRANGE', KEYS[1], 0, -1)";
        List<String> jsonMessages = commands.eval(script, ScriptOutputType.MULTI, new String[]{getDialogKey(userId, friendId)});

        List<Message> messages = new ArrayList<>();
        for (String jsonMessage : jsonMessages) {
            try {
                Message message = objectMapper.readValue(jsonMessage, Message.class);
                messages.add(message);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return messages;
    }

    private static String getDialogKey(UUID userId, UUID friendId) {
        return "dialog:" + userId.toString() + ":" + friendId.toString();
    }
}