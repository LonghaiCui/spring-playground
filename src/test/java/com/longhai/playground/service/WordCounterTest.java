package com.longhai.playground.service;

import com.longhai.playground.config.WordConfig;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class WordCounterTest {
    @InjectMocks
    WordCounter wordCounter;

    @Mock
    WordConfig wordConfig;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testWordCount() throws Exception {
        when(wordConfig.isCaseSensitive())
                .thenReturn(true);
        when(wordConfig.getSkip())
                .thenReturn(asList(""));
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
        when(wordConfig.isCaseSensitive())
                .thenReturn(true);
        when(wordConfig.getSkip())
                .thenReturn(asList(""));

        Map<String, Integer> count = wordCounter.count("ccc");

        assertEquals(count.get("How"), new Integer(1));
        assertEquals(count.get("now"), new Integer(1));
        assertEquals(count.get("brown"), new Integer(1));
        assertEquals(count.get("cow"), new Integer(1));
    }

    @Test
    public void testWordCountSkip() throws Exception {
        when(wordConfig.isCaseSensitive())
                .thenReturn(false);
        when(wordConfig.getSkip())
                .thenReturn(asList(""));

        Map<String, Integer> count = wordCounter.count("The BROWN cow jumps over a brown fox");

        assertEquals(count.get("brown"), new Integer(2));
        assertEquals(count.get("cow"), new Integer(1));
        assertEquals(count.get("jumps"), new Integer(1));
        assertEquals(count.get("over"), new Integer(1));
        assertEquals(count.get("fox"), new Integer(1));
    }
}