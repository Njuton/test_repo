package com.example.myapp.dao;

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
        String sql = "UPDATE counters SET unread_count = unread_count + 1 WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }

    public void decrementCounter(UUID userId) {
        String sql = "UPDATE counters SET unread_count = unread_count - 1 WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }
}