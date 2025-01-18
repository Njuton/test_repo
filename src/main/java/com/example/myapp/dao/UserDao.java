package com.example.myapp.dao;

import com.example.myapp.dao.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User save(User user) {
        String sql = "INSERT INTO users (id, username, first_name, last_name, birthdate, biography, city, password) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        user.setId(UUID.randomUUID());
        jdbcTemplate.update(sql,
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getBiography(),
                user.getCity(),
                user.getPassword()
        );
        return user;
    }

    public boolean isUserExist(String username) {
        String sql = "SELECT count(*) FROM users WHERE username = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }

    public Optional<User> getUserById(UUID id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), id);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }
}