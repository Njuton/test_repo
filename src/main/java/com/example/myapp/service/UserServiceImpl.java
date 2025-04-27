package com.example.myapp.service;

import com.example.myapp.dao.FriendDao;
import com.example.myapp.dao.UserDao;
import com.example.myapp.dao.entity.User;
import com.example.myapp.exception.UserAlreadyExistsException;
import com.example.myapp.exception.UserNotFoundException;
import com.example.myapp.service.dto.RegisterRequest;
import com.example.myapp.service.dto.UserDto;
import jakarta.annotation.Nullable;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author rmartynov
 * @since 14.01.2025
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final FriendDao friendDao;

    public UserServiceImpl(UserDao userDao, FriendDao friendDao) {
        this.userDao = userDao;
        this.friendDao = friendDao;
    }

    @Override
    public UUID registerUser(RegisterRequest request) {

        if (userDao.isUserExist(request.username())) {
            throw new UserAlreadyExistsException("User with username " + request.username() + " already exists");
        }

        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setBirthdate(request.birthdate());
        user.setBiography(request.biography());
        user.setCity(request.city());
        user.setUsername(request.username());

        // Хэшируем пароль перед сохранением
        String hashedPassword = BCrypt.hashpw(request.password(), BCrypt.gensalt());
        user.setPassword(hashedPassword);

        User savedUser = userDao.save(user);

        return savedUser.getId();
    }

    @Override
    public UserDto getUserById(UUID id) {
        User user = userDao.getUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getBiography(),
                user.getCity()
        );
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userDao.findAll()
                .stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getUsername(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getBirthdate(),
                        user.getBiography(),
                        user.getCity()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> search(@Nullable String firstNamePrefix, @Nullable String lastNamePrefix) {
        return userDao.search(firstNamePrefix, lastNamePrefix).stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getUsername(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getBirthdate(),
                        user.getBiography(),
                        user.getCity()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void addFriend(UUID userId, UUID friendId) {
        if (friendDao.isFriend(userId, friendId)) {
            throw new RuntimeException("Users are already friends");
        }
        friendDao.addFriend(userId, friendId);
    }

    @Override
    public void removeFriend(UUID userId, UUID friendId) {
        if (!friendDao.isFriend(userId, friendId)) {
            throw new RuntimeException("Users are not friends");
        }
        friendDao.removeFriend(userId, friendId);
    }

    @Override
    public List<UserDto> getFriends(UUID userId) {
        return friendDao.getFriends(userId).stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getUsername(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getBirthdate(),
                        user.getBiography(),
                        user.getCity()
                ))
                .collect(Collectors.toList());
    }
}
