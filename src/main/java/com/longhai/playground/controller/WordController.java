package com.longhai.playground.controller;

import com.longhai.playground.config.MyConfig;
import com.longhai.playground.service.WordCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class WordController {
    @Autowired
    WordCounter wordCounter;

    @Autowired
    MyConfig myConfig;

    @RequestMapping("/words/count")
    public Map<String, Integer> countWords(@RequestBody String message){
        Map<String, Integer> result = wordCounter.count(message);
        return result;
    }

    @RequestMapping("/myConfig")
    public String getConfig(){
        String config = myConfig.getFirstName() + " " + myConfig.getLastName();

        List<Integer> foo = myConfig.getFoo();

        int sum = foo.stream()
                .mapToInt(x -> x)
                .sum();

        return config + sum;
    }

}
