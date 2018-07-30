package com.longhai.playground.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.longhai.playground.model.LessonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@WebMvcTest(LessonsController.class)
//@AutoConfigureMockMvc

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class LessonsControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    LessonRepository repository;

    ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
    }

//    @Transactional
//    @Rollback
//    @Test
//    public void testPostLesson() throws Exception {
//        String request = getJSON("/lesson1.json");
//        String requestUpdate = getJSON("/lessonUpdate.json");
//
//        mockMvc.perform(post("/lessons")
//                .accept(MediaType.APPLICATION_JSON_UTF8)
//                .content(request)
//                .contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk());
//
//        mockMvc.perform(get("/lessons/1")
//                .accept(MediaType.APPLICATION_JSON_UTF8)
//                .contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title", is("Requests and Responses")));
//
//        mockMvc.perform(patch("/lessons/1")
//                .accept(MediaType.APPLICATION_JSON_UTF8)
//                .content(requestUpdate)
//                .contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk());
//
//        mockMvc.perform(get("/lessons/1")
//                .accept(MediaType.APPLICATION_JSON_UTF8)
//                .contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title", is("Spring Security")));
//
//        repository.deleteAll();
//
//    }

    @Transactional
    @Rollback
    @Test
    public void testCustomMethods() throws Exception {
        String request = getJSON("/lessons.json");

        mockMvc.perform(post("/lessons/list")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        mockMvc.perform(get("/lessons/find/Spring Security")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Spring Security")));

        repository.deleteAll();

    }


    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }
}