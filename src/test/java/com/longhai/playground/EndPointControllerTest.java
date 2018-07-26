package com.longhai.playground;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EndPointController.class)
public class EndPointControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testMyEndPoint() throws Exception {
        mvc.perform(get("/").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Get to work"));
    }

    @Test
    public void testPerson() throws Exception {
        mvc.perform(get("/person")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName", is("Johnson")))
                .andExpect(jsonPath("$.firstName", is("Dwayne")));
    }

    @Test
    public void testFlight() throws Exception {
        mvc.perform(get("/flights")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tickets[0].passenger.lastName", is("Some other name")))
                .andExpect(jsonPath("$[0].tickets[0].passenger.firstName", is("Some name")));
    }
}