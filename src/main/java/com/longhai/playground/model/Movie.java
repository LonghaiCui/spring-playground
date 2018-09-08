package com.longhai.playground.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Movie {
    @JsonProperty("Title")
    private String title;
    @JsonProperty("imdbID")
    private String imdbId;
    @JsonProperty("Poster")
    private String poster;
    @JsonProperty("Year")
    private int year;


}
