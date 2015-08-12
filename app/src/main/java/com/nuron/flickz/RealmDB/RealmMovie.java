package com.nuron.flickz.RealmDB;

import io.realm.RealmObject;

/**
 * Created by sunil on 10-Aug-15.
 */
public class RealmMovie extends RealmObject {

    private String backdropPath;
    private int id;
    private String overview;
    private String release_date;
    private String poster_path;
    private double popularity;
    private String title;
    private double vote_average;

    public RealmMovie() {
        this.backdropPath = "";
        this.id = 0;
        this.overview = "";
        this.release_date = "";
        this.poster_path = "";
        this.popularity = 0;
        this.title = "";
        this.vote_average = 0;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

}