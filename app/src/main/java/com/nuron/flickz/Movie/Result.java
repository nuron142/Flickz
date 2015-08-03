package com.nuron.flickz.Movie;

public class Result {

    private Object backdropPath;
    private Integer id;
    private String overview;
    private String release_date;
    private Object posterPath;
    private Double popularity;
    private String title;
    private Double vote_average;

    /**
     *
     * @return
     * The backdropPath
     */
    public Object getBackdropPath() {
        return backdropPath;
    }

    /**
     *
     * @param backdropPath
     * The backdrop_path
     */
    public void setBackdropPath(Object backdropPath) {
        this.backdropPath = backdropPath;
    }


    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }


    /**
     *
     * @return
     * The overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     *
     * @return
     * The releaseDate
     */
    public String getReleaseDate() {
        return release_date;
    }


    /**
     *
     * @return
     * The posterPath
     */
    public Object getPosterPath() {
        return posterPath;
    }


    /**
     *
     * @return
     * The popularity
     */
    public Double getPopularity() {
        return popularity;
    }


    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }


    /**
     *
     * @return
     * The voteAverage
     */
    public Double getVoteAverage() {
        return vote_average;
    }


}