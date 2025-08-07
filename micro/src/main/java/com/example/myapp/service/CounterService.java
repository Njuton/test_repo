package com.example.myapp.service;

import com.example.myapp.dao.CounterDao;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author rmartynov
 * @since 07.08.2025
 */
@Service
public class CounterService {

    private final CounterDao counterDao;

    public CounterService(CounterDao counterDao) {
        this.counterDao = counterDao;
    }

    public void incrementCounter(UUID userId) {
        counterDao.incrementCounter(userId);
    }

    public void decrementCounter(UUID userId) {
        counterDao.decrementCounter(userId);
    }
}