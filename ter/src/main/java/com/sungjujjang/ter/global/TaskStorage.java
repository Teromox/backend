package com.sungjujjang.ter.global;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Random;

@Component
public class TaskStorage {
    private final Map<String, Boolean> storage = new ConcurrentHashMap<>();

    public String makeKey() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder key = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 9; i++) {
            int index = random.nextInt(chars.length());
            key.append(chars.charAt(index));
        }

        return key.toString();
    }
    public void save(String key, Boolean value) { storage.put(key, value); }
    public Boolean get(String key) {
        return storage.getOrDefault(key, Boolean.FALSE);
    }
    public void delete(String key) {
        storage.remove(key);
    }
}
