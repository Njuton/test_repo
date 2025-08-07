package com.example.myapp.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author rmartynov
 * @since 07.08.2025
 */
@Repository
public class CounterDao {

    private final JdbcTemplate jdbcTemplate;

    public CounterDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void incrementCounter(UUID userId) {
        ensureUserExists(userId);
        String sql = "UPDATE counters SET unread_count = unread_count + 1 WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }

    public void decrementCounter(UUID userId, int count) {
        ensureUserExists(userId);
        String sql = "UPDATE counters SET unread_count = unread_count - ? WHERE user_id = ?";
        jdbcTemplate.update(sql, count, userId);
    }

    public int getCounter(UUID userId) {
        String sql = "SELECT unread_count FROM counters WHERE user_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{userId}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    private void ensureUserExists(UUID userId) {
        String checkSql = "SELECT COUNT(*) FROM counters WHERE user_id = ?";
        Integer count = jdbcTemplate.queryForObject(checkSql, new Object[]{userId}, Integer.class);

        if (count == null || count == 0) {
            String insertSql = "INSERT INTO counters (id, user_id, unread_count) VALUES (?, ?, 0)";
            jdbcTemplate.update(insertSql, UUID.randomUUID(), userId);
        }
    }
}