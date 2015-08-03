package com.nuron.flickz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nuron.flickz.Adapters.MovieRecyclerAdapter;
import com.nuron.flickz.MovieDB.Movie;
import com.nuron.flickz.MovieDB.MovieList;
import com.nuron.flickz.RetrofitService.MovieDBService;
import com.nuron.flickz.RetrofitService.ServiceFactory;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Homepage extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        /**
         * Set up Android CardView/RecycleView
         */
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final MovieRecyclerAdapter movieRecyclerAdapter = new MovieRecyclerAdapter(this);
        mRecyclerView.setAdapter(movieRecyclerAdapter);

        /**
         * START: button set up
         */
        Button bClear = (Button) findViewById(R.id.button_clear);
        Button bFetch = (Button) findViewById(R.id.button_fetch);
        bClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                movieRecyclerAdapter.clear();
            }
        });

        bFetch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                MovieDBService service = ServiceFactory.createRetrofitService(MovieDBService.class, MovieDBService.SERVICE_ENDPOINT);
//                service.movieByID("210479",MovieDBService.API_KEY)
//                        .subscribeOn(Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Subscriber<Movie>() {
//                            @Override
//                            public final void onCompleted() {
//                                // do nothing
//                            }
//
//                            @Override
//                            public final void onError(Throwable e) {
//                                Log.e("GithubDemo", e.getMessage());
//                            }
//
//                            @Override
//                            public final void onNext(Movie response) {
//                                Log.d("1","Movie Title = "+response.getTitle()+", Release date = "+response.getReleaseDate()+" Vote = "+response.getVoteAverage());
//                                movieRecyclerAdapter.addData(response);
//                            }
//                        });

                service.searchMovie("Locke", MovieDBService.API_KEY)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<MovieList>() {
                            @Override
                            public final void onCompleted() {
                                // do nothing
                            }

                            @Override
                            public final void onError(Throwable e) {
                                Log.e("GithubDemo", e.getMessage());
                            }

                            @Override
                            public final void onNext(MovieList response) {
                               // Log.d("1","Movie Title = "+response.getTitle()+", Release date = "+response.getReleaseDate()+" Vote = "+response.getVoteAverage());
                                //movieRecyclerAdapter.addData(response);
//                                int i=0;
//                                for(i=0; i<response.getTotalPages();i++)
//                                {
                                List<Movie> movies = response.getResults();
                                for (Movie movie : movies)
                                    {
                                        Log.d("1", "Movie Title = " + movie.getTitle() + ", Release date = "
                                                + movie.getReleaseDate() + " Vote = " + movie.getVoteAverage() + " PosterPath= https://image.tmdb.org/t/p/w185" + movie.getPosterPath());
                                        movieRecyclerAdapter.addData(movie);
                                    }
                                //}

                            }
                        });

            }
        });
    }

}
