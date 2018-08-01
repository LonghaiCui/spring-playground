package com.longhai.playground.service;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WordCounter {
    public WordCounter() {
    }

    public Map<String, Integer> count(String message) {
        Map<String, Integer> result = new HashMap<>();

        String[] split = message.replaceAll("[^a-zA-Z ]", "").split("\\s+");
        for(String s: split) {
            if(!result.keySet().contains(s)) {
                result.put(s, 1);
            } else {
                result.put(s, result.get(s) + 1);
            }
        }
        return result;
    }
}
