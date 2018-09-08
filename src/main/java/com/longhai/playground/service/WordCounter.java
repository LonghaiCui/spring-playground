package com.longhai.playground.service;


import com.longhai.playground.config.WordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class WordCounter {

    private WordConfig wordConfig;

    @Autowired
    public WordCounter(WordConfig wordConfig) {
        this.wordConfig = wordConfig;
    }


    public Map<String, Integer> count(String message) {

        String newMessage = wordConfig.isCaseSensitive() ? message : message.toLowerCase();

        List<String> skip = wordConfig.getSkip();

        Map<String, Integer> result = new HashMap<>();

        String[] split = newMessage.replaceAll("[^a-zA-Z ]", "").split("\\s+");
        for(String s: split) {
            if (skip.contains(s)) continue;
            if(!result.keySet().contains(s)) {
                result.put(s, 1);
            } else {
                result.put(s, result.get(s) + 1);
            }
        }
        return result;
    }
}
