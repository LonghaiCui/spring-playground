package com.longhai.playground.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MathController.class)

public class MathControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    public void testPI() throws Exception {
        mvc.perform(get("/math/pi").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }

    @Test
    public void testCalculate() throws Exception {
        mvc.perform(get("/math/calculate?operation=add&x=4&y=6").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));

        mvc.perform(get("/math/calculate?operation=multiply&x=4&y=6").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("4 * 6 = 24"));

        mvc.perform(get("/math/calculate?operation=subtract&x=4&y=6").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("4 - 6 = -2"));

        mvc.perform(get("/math/calculate?operation=divide&x=30&y=6").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("30 / 6 = 5"));

        mvc.perform(get("/math/calculate?x=4&y=6").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));
    }

    @Test
    public void testSum() throws Exception {
        mvc.perform(get("/math/sum?n=4&n=5&n=6").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 5 + 6 = 15"));
    }

    @Test
    public void testVolume() throws Exception {
        mvc.perform(get("/math/volume/3/4/5").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));

        mvc.perform(patch("/math/volume/6/7/8").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 6x7x8 rectangle is 336"));
    }


    @Test
    public void testArea() throws Exception {
        mvc.perform(post("/math/area?type=circle&radius=4")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a circle with a radius of 4 is 50.26548"));

        mvc.perform(post("/math/area?type=rectangle&width=4&height=7")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a 4x7 rectangle is 28"));

        mvc.perform(post("/math/area?type=rectangle&radius=4")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid"));
    }
}