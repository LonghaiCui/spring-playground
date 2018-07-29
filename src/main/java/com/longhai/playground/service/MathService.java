package com.longhai.playground.service;

import java.util.List;
import java.util.stream.Collectors;

public class MathService {
    public static String calculate(String operation, int x, int y) {
        switch (operation == null ? "default" : operation) {
            case "add":
                return x + " + " + y + " = " + (x + y);
            case "multiply":
                return x + " * " + y + " = " + (x * y);
            case "subtract":
                return x + " - " + y + " = " + (x - y);
            case "divide":
                return x + " / " + y + " = " + (x / y);
            default:
                return x + " + " + y + " = " + (x + y);
        }
    }

    public static String sum(List<String> numbers) {
        return numbers.stream().collect(Collectors.joining(" + "))
                + " = " +
                numbers.stream().mapToInt(x -> Integer.parseInt(x)).sum();
    }
}
