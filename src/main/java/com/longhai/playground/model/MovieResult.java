package com.longhai.playground.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieResult {
    @JsonProperty("Search")
    private List<Movie> search;

    @JsonProperty("totalResults")
    private String totalResults;

    @JsonProperty("Response")
    private boolean isResponse;

    public MovieResult() {
    }

    public List<Movie> getSearch() {
        return search;
    }

    public void setSearch(List<Movie> search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public boolean isResponse() {
        return isResponse;
    }

    public void setResponse(boolean response) {
        isResponse = response;
    }
}
