package com.longhai.playground.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "playground.config")
public class MyConfig {
    private String firstName;
    private String lastName;

    private List<Integer> foo;

    public List<Integer> getFoo() {
        return foo;
    }

    public void setFoo(List<Integer> foo) {
        this.foo = foo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
