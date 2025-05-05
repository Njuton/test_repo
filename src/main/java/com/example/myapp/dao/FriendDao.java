package com.example.myapp.dao;

import com.example.myapp.dao.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class FriendDao {

    private final JdbcTemplate jdbcTemplate;

    public FriendDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addFriend(UUID userId, UUID friendId) {
        String sql = "INSERT INTO user_friends (user_id, friend_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, friendId);
        jdbcTemplate.update(sql, friendId, userId);
    }

    public void removeFriend(UUID userId, UUID friendId) {
        String sql1 = "DELETE FROM user_friends WHERE user_id = ? AND friend_id = ?";
        jdbcTemplate.update(sql1, userId, friendId);
        jdbcTemplate.update(sql1, friendId, userId);
    }

    public boolean isFriend(UUID userId, UUID friendId) {
        String sql = "SELECT COUNT(*) FROM user_friends WHERE user_id = ? AND friend_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId, friendId);
        return count != null && count > 0;
    }

    public List<User> getFriends(UUID userId) {
        String sql = "SELECT * FROM users u INNER JOIN user_friends uf ON u.id = uf.friend_id where uf.user_id = ?";
        return jdbcTemplate.query(sql, new UserRowMapper(), userId);
    }
}
