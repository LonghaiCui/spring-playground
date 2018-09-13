package com.longhai.playground.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(secure=false)
@WebMvcTest(EmployeesController.class)

public class EmployeesControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    public void testPI() throws Exception {
        mvc.perform(get("/employees")
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().string("Super secret list of employees"));
    }
}