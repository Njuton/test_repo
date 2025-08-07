package com.example.myapp.dao.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "counters")
public class Counter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "user_id", unique = true, nullable = false)
    private UUID userId;

    @Column(name = "unread_count", nullable = false)
    private int unreadCount;

    // Конструкторы, геттеры и сеттеры

    public Counter() {
    }

    public Counter(UUID userId, int unreadCount) {
        this.userId = userId;
        this.unreadCount = unreadCount;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }
}
