package com.longhai.playground.controller;

import com.longhai.playground.service.WordCounter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WordControllerMockMvcTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    WordCounter wordCounter;

    @Before
    public void setUp() throws Exception {
        when(wordCounter.count("A brown cow jumps over a brown fox"))
                .thenReturn(new HashMap(){{
                    put("over", 1);
                }});

    }

    @Test
    public void testWordCount() throws Exception {
        mockMvc.perform(post("/words/count")
                .accept(MediaType.APPLICATION_JSON)
                .content("A brown cow jumps over a brown fox"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"over\":1}"));
    }
}