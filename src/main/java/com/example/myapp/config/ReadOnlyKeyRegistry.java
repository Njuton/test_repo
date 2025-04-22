package com.example.myapp.config;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Реестр всех ключей для read-only датасорсов
 */
@Component
public class ReadOnlyKeyRegistry {

    private final Set<String> keySet;
    private final Random random;

    public ReadOnlyKeyRegistry() {
        this.keySet = new HashSet<>();
        this.random = new Random();
    }

    /**
     * Добавляет ключ в реестр.
     *
     * @param key Ключ для добавления.
     */
    public void addKey(String key) {
        keySet.add(key);
    }

    public boolean isEmpty() {
        return keySet.isEmpty();
    }

    /**
     * Получает случайный ключ из реестра.
     *
     * @return случайный ключ или null, если реестр пуст.
     */
    public String getRandomKey() {
        int index = random.nextInt(keySet.size());
        return keySet.stream().skip(index).findFirst().get();
    }
}
