package com.longhai.playground.controller;

import com.longhai.playground.service.WordCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WordController {
    @Autowired
    WordCounter wordCounter;

    @RequestMapping("/words/count")
    public Map<String, Integer> countWords(@RequestBody String message){
        Map<String, Integer> result = wordCounter.count(message);
        return result;
    }
}
