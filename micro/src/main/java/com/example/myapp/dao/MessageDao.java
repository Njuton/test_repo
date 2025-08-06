package com.example.myapp.dao;

import com.example.myapp.dao.entity.Message;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * @author rmartynov
 * @since 04.05.25
 */
@Repository
public class MessageDao {

    private final JdbcTemplate jdbcTemplate;

    public MessageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveMessage(Message message) {
        String sql = "INSERT INTO messages (id, sender_id, receiver_id, text, created_at) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                message.getId(),
                message.getSenderId(),
                message.getReceiverId(),
                message.getText(),
                message.getCreatedAt());
    }

    public List<Message> getDialog(UUID userId, UUID friendId) {
        String sql = "SELECT * FROM messages " +
                "WHERE (sender_id = ? AND receiver_id = ?) OR (sender_id = ? AND receiver_id = ?) " +
                "ORDER BY created_at ASC";
        return jdbcTemplate.query(sql, new MessageRowMapper(), userId, friendId, friendId, userId);
    }

    private static class MessageRowMapper implements RowMapper<Message> {
        @Override
        public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
            Message message = new Message();
            message.setId(UUID.fromString(rs.getString("id")));
            message.setSenderId(UUID.fromString(rs.getString("sender_id")));
            message.setReceiverId(UUID.fromString(rs.getString("receiver_id")));
            message.setText(rs.getString("text"));
            message.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            return message;
        }
    }
}
