package com.longhai.playground.service;

import com.longhai.playground.model.Movie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest

public class MovieServiceTest {
    @Autowired
    MovieService movieService;

    MockRestServiceServer mockServer;

    @Before
    public void setUp() throws Exception {
        mockServer = MockRestServiceServer.createServer(movieService.getRestTemplate());

        mockServer
                .expect(requestTo("http://www.omdbapi.com/?s=harry&apikey=d39689f3"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(getJSON("/movies.json"), MediaType.APPLICATION_JSON));
    }

    @Test
    public void testMovieService() throws Exception {
        List<Movie> movies = movieService.getMovies("harry");

        assertEquals(2, movies.size());
        assertEquals("Harry Potter and the Deathly Hallows: Part 2", movies.get(0).getTitle());
        assertEquals("Harry Potter and the Sorcerer's Stone", movies.get(1).getTitle());

        mockServer.verify();


    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }

}