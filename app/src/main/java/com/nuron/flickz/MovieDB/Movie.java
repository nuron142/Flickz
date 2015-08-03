package com.nuron.flickz.MovieDB;

public class Movie {

    private Object backdropPath;
    private Integer id;
    private String overview;
    private String release_date;
    private String poster_path;
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
    public String getPosterPath() {
        return poster_path;
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