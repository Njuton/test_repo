package com.example.myapp.dao;

import com.example.myapp.dao.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author rmartynov
 * @since 15.01.2025
 */
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(UUID.fromString(rs.getString("id")));
        user.setUsername(rs.getString("username"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setBirthdate(rs.getDate("birthdate") != null ? rs.getDate("birthdate").toLocalDate() : null);
        user.setBiography(rs.getString("biography"));
        user.setCity(rs.getString("city"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}
