package com.nuron.flickz;

import com.nuron.flickz.Movie.MovieDB;
import com.nuron.flickz.Movie.Result;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by sunil on 03-Aug-15.
 */
public interface MovieDBService {
    String SERVICE_ENDPOINT = "https://api.themoviedb.org/3";
    String API_KEY= "80da0a99415ef0ec9d8d313dab77fbf7";

    @GET("/search/movie")
    Observable<MovieDB> searchMovie(@Query("query") String query,@Query("api_key") String api_key );


    @GET("/movie/{id}")
    Observable<Result> movieByID(@Path("id") String id,@Query("api_key") String api_key );

}