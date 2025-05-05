package com.example.myapp.service;

import com.example.myapp.dao.MessageDao;
import com.example.myapp.dao.entity.Message;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author rmartynov
 * @since 04.05.25
 */
@Service
public class DialogService {

    private final MessageDao messageDao;

    public DialogService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public void sendMessage(UUID senderId, UUID receiverId, String text) {
        Message message = new Message();
        message.setId(UUID.randomUUID());
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setText(text);
        message.setCreatedAt(LocalDateTime.now());
        messageDao.saveMessage(message);
    }

    public List<Message> getDialog(UUID userId, UUID friendId) {
        return messageDao.getDialog(userId, friendId);
    }
}
