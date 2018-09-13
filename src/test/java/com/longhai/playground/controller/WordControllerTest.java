package com.longhai.playground.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(secure=false)
@SpringBootTest

public class WordControllerTest {
    @Autowired
    WordController wordController;

    @Ignore
    @Test
    public void testWordCount() throws Exception {
        Map<String, Integer> count = wordController.countWords("A  brown cow jumps over a brown fox");

        assertEquals(count.get("A"), new Integer(1));
        assertEquals(count.get("brown"), new Integer(2));
        assertEquals(count.get("cow"), new Integer(1));
        assertEquals(count.get("jumps"), new Integer(1));
        assertEquals(count.get("over"), new Integer(1));
        assertEquals(count.get("a"), new Integer(1));
        assertEquals(count.get("fox"), new Integer(1));
    }
}