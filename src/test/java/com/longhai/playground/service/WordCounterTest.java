package com.longhai.playground.service;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;


public class WordCounterTest {
    WordCounter wordCounter;
    @Before
    public void setUp() throws Exception {
        wordCounter = new WordCounter();
    }

    @Test
    public void testWordCount() throws Exception {
        Map<String, Integer> count = wordCounter.count("A  brown cow jumps over a brown fox");

        assertEquals(count.get("A"), new Integer(1));
        assertEquals(count.get("brown"), new Integer(2));
        assertEquals(count.get("cow"), new Integer(1));
        assertEquals(count.get("jumps"), new Integer(1));
        assertEquals(count.get("over"), new Integer(1));
        assertEquals(count.get("a"), new Integer(1));
        assertEquals(count.get("fox"), new Integer(1));
    }

    @Test
    public void testWordCountPunctuation() throws Exception {
        Map<String, Integer> count = wordCounter.count("How now, brown cow");

        assertEquals(count.get("How"), new Integer(1));
        assertEquals(count.get("now"), new Integer(1));
        assertEquals(count.get("brown"), new Integer(1));
        assertEquals(count.get("cow"), new Integer(1));
    }
}