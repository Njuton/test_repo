package com.example.myapp.service;

import com.example.myapp.dao.UserDao;
import com.example.myapp.dao.entity.User;
import com.example.myapp.exception.UserNotFoundException;
import com.example.myapp.service.dto.LoginRequest;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author rmartynov
 * @since 14.01.2025
 */
@Service
public class AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDao userDao;

    public AuthService(JwtTokenProvider jwtTokenProvider, UserDao userDao) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDao = userDao;
    }

    public String login(LoginRequest loginRequest) {
        UUID userId = loginRequest.id();
        User user = userDao.getUserById(userId).orElseThrow(
                () -> new UserNotFoundException("User with ID " + userId + " not found"));

        if (!BCrypt.checkpw(loginRequest.password(), user.getPassword())) {
            throw new RuntimeException("Password is incorrect");
        }

        return jwtTokenProvider.generateToken(loginRequest.id(), user.getUsername());
    }
}