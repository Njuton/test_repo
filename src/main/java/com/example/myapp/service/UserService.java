package com.example.myapp.service;

import com.example.myapp.service.dto.RegisterRequest;
import com.example.myapp.service.dto.UserDto;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.UUID;

/**
 * @author rmartynov
 * @since 14.01.2025
 */
public interface UserService {
    UUID registerUser(RegisterRequest request);

    UserDto getUserById(UUID id);

    List<UserDto> getAllUsers();

    List<UserDto> search(String firstName, String lastName);

    void addFriend(UUID userId, UUID friendId);

    void removeFriend(UUID userId, UUID friendId);

    List<UserDto> getFriends(UUID userId);
}