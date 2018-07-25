package com.longhai.playground;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class EndPointController {

    @RequestMapping("/")
    public String getMyEndPoint() {
        return "Get to work";
    }
}
