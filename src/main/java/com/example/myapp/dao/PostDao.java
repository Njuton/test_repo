package com.example.myapp.dao;

import com.example.myapp.dao.entity.Post;
import com.example.myapp.dao.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository
public class PostDao {

    private final JdbcTemplate jdbcTemplate;

    public PostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Post save(Post post) {
        String sql = "INSERT INTO posts (id, user_id, text, created_at) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, post.getId(), post.getUser().getId(), post.getText(), post.getCreatedAt());
        return post;
    }

    public void update(Post post) {
        String sql = "UPDATE posts SET text = ? WHERE id = ?";
        jdbcTemplate.update(sql, post.getText(), post.getId());
    }

    public void delete(UUID postId) {
        String sql = "DELETE FROM posts WHERE id = ?";
        jdbcTemplate.update(sql, postId);
    }

    public Post getById(UUID postId) {
        String sql = "SELECT * FROM posts WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new PostRowMapper(), postId);
    }

    public List<Post> getByUserId(UUID userId) {
        String sql = "SELECT * FROM posts WHERE user_id = ?";
        return jdbcTemplate.query(sql, new PostRowMapper(), userId);
    }

    public List<UUID> getFriendsPostsIds(List<UUID> friendIds, int offset, int limit) {
        if (friendIds.isEmpty()) {
            return Collections.emptyList(); // Возвращаем пустой список, если друзей нет
        }

        StringBuilder sql = new StringBuilder("SELECT id FROM posts WHERE user_id IN (");
        for (int i = 0; i < friendIds.size(); i++) {
            sql.append("?");
            if (i < friendIds.size() - 1) {
                sql.append(",");
            }
        }
        sql.append(") ORDER BY created_at DESC OFFSET ? LIMIT ?");

        Object[] params = new Object[friendIds.size() + 2];
        for (int i = 0; i < friendIds.size(); i++) {
            params[i] = friendIds.get(i);
        }
        params[friendIds.size()] = offset;
        params[friendIds.size() + 1] = limit;

        return jdbcTemplate.queryForList(sql.toString(), UUID.class, params);
    }

    private static class PostRowMapper implements RowMapper<Post> {
        @Override
        public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
            Post post = new Post();
            post.setId(UUID.fromString(rs.getString("id")));
            User user = new User();
            user.setId(UUID.fromString(rs.getString("user_id")));
            post.setUser(user);
            post.setText(rs.getString("text"));
            post.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            return post;
        }
    }
}
