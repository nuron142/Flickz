package com.nuron.flickz;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by sunil on 30-Jul-15.
 */
public interface GithubService {
    String SERVICE_ENDPOINT = "https://api.github.com";

    @GET("/users/{login}")
    Observable<Github> getUser(@Path("login") String login);

}