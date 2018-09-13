package com.longhai.playground.controller;

import com.longhai.playground.model.Movie;
import com.longhai.playground.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
//Movie Controller
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping("/zen")
    public String getZen(){
        return movieService.getMessage();
    }

    @GetMapping("/movies")
    public List<Movie> getMovies(@RequestParam String q){
        return movieService.getMovies(q);
    }
}
