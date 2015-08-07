package com.nuron.flickz.MovieDB;

import java.util.ArrayList;
import java.util.List;

public class MovieList {

    private Integer page;
    private List<Movie> results = new ArrayList<>();
    private Integer totalPages;
    private Integer totalResults;

    /**
     *
     * @return
     * The page
     */
    public Integer getPage() {
        return page;
    }


    /**
     *
     * @return
     * The movies
     */
    public List<Movie> getResults() {
        return results;
    }

    /**
     *
     * @return
     * The totalPages
     */
    public Integer getTotalPages() {
        return totalPages;
    }


    /**
     *
     * @return
     * The totalResults
     */
    public Integer getTotalResults() {
        return totalResults;
    }

}