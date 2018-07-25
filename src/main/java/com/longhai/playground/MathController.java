package com.longhai.playground;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @RequestMapping(path = "/volume/{length}/{width}/{height}")
    public String getVolume(@PathVariable Map<String, String> values) {
        return "The volume of a " + values.get("length") + "x"+ values.get("width") + "x"+ values.get("height") + " rectangle is " + Integer.parseInt(values.get("length")) * Integer.parseInt(values.get("width")) * Integer.parseInt(values.get("height"));
    }
}
