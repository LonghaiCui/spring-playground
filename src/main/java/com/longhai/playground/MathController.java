package com.longhai.playground;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {


    @GetMapping(path = "/pi")
    public String getPI() {
        return "3.141592653589793";
    }


    @GetMapping(path = "/calculate")
    public String calculate(
            @RequestParam(required = false) String operation,
            @RequestParam int x,
            @RequestParam int y) {
        return MathService.calculate(operation, x, y);
    }

    @GetMapping(path = "/sum")
    public String getSum(@RequestParam MultiValueMap<String, String> values) {
        return MathService.sum(values.get("n"));
    }
}
