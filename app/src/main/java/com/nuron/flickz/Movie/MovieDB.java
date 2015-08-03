package com.nuron.flickz.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MovieDB {

    private Integer page;
    private List<Result> results = new ArrayList<Result>();
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
     * The results
     */
    public List<Result> getResults() {
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