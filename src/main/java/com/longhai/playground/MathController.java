package com.longhai.playground;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    @GetMapping(path = "/math/pi")
    public String getPI() {
        return "3.141592653589793";
    }
}
