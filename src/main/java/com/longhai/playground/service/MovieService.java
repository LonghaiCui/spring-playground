package com.longhai.playground.service;

import com.longhai.playground.model.Movie;
import com.longhai.playground.model.MovieResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MovieService {
    private final RestTemplate restTemplate = new RestTemplate();

    public String getMessage() {
        return this.restTemplate.getForObject(
                "https://api.github.com/zen",
                String.class
        );
    }

    public List<Movie> getMovies(String search) {
        MovieResult movieResult = this.restTemplate.getForObject(
                "http://www.omdbapi.com/?s={search}&apikey=d39689f3",
                MovieResult.class,
                search
        );

        return movieResult.getSearch();
    }


}
