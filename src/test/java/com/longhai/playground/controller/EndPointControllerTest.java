package com.longhai.playground.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(EndPointController.class)
public class EndPointControllerTest {
    @Autowired
    private MockMvc mvc;

    private static ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
    }

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
                .andExpect(jsonPath("$.firstName", is("Dwayne")))
                .andExpect(jsonPath("$.lastName", is("Johnson")));
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

    @Test
    public void testFlightSumFromString() throws Exception {
        String request = " {\n" +
                "    \"tickets\": [\n" +
                "      {\n" +
                "        \"passenger\": {\n" +
                "          \"firstName\": \"Some name\",\n" +
                "          \"lastName\": \"Some other name\"\n" +
                "        },\n" +
                "        \"price\": 200\n" +
                "      },\n" +
                "      {\n" +
                "        \"passenger\": {\n" +
                "          \"firstName\": \"Name B\",\n" +
                "          \"lastName\": \"Name C\"\n" +
                "        },\n" +
                "        \"price\": 150\n" +
                "      }\n" +
                "    ]\n" +
                "  }";

        mvc.perform(post("/flights/tickets/total").content(request)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(350)));
    }

    @Test
    public void testFlightSumFromObectMapper() throws Exception {
        EndPointController.Person passenger1 = new EndPointController.Person("Some name", "Some other name");
        EndPointController.Person passenger2 = new EndPointController.Person("Some other name", null);
        EndPointController.Flight flight1 = new EndPointController.Flight();

        flight1.setTickets(asList(new EndPointController.Ticket(passenger1, 200),
                new EndPointController.Ticket(passenger2, 150)));

        String request = objectMapper.writeValueAsString(flight1);
        mvc.perform(post("/flights/tickets/total").content(request)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(350)));
    }

    @Test
    public void testFlightSumFromFile() throws Exception {
        String request = getJSON("/data.json");

        mvc.perform(post("/flights/tickets/total").content(request)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(350)));
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }

}