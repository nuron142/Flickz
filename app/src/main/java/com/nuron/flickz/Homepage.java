package com.nuron.flickz;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;

import com.nuron.flickz.Adapters.MovieRecyclerAdapter;
import com.nuron.flickz.MovieDB.Movie;
import com.nuron.flickz.MovieDB.MovieList;
import com.nuron.flickz.RealmDB.RealmMovie;
import com.nuron.flickz.RetrofitService.MovieDBService;
import com.nuron.flickz.RetrofitService.ServiceFactory;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Homepage extends AppCompatActivity{

    @Bind(R.id.search)
    EditText searchEditText;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    MovieRecyclerAdapter movieRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ButterKnife.bind(this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        movieRecyclerAdapter = new MovieRecyclerAdapter(this);
        mRecyclerView.setAdapter(movieRecyclerAdapter);

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        searchMovie();
                    }

                });

    }

    public void clear() {
        clearCache();
        movieRecyclerAdapter.clear();
        movieRecyclerAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.button_search)
    public void searchClick() {
        searchMovie();
    }


    private void searchMovie() {
        if (isNetConnected()) {
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(true);
                }
            });

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
            service.searchMovie(searchEditText.getText().toString(), MovieDBService.API_KEY)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<MovieList>() {
                        @Override
                        public final void onCompleted() {
                            // do nothing
                        }

                        @Override
                        public final void onError(Throwable e) {
                            Log.e("Flickz Error : ", e.getMessage());
                        }

                        @Override
                        public final void onNext(MovieList response) {

                            clear();
                            movieRecyclerAdapter.clear();
                            swipeRefreshLayout.setRefreshing(false);
                            List<Movie> movies = response.getResults();
                            for (Movie movie : movies) {
                                movieRecyclerAdapter.addData(movie);
                                addToDB(movie);
                            }
                        }
                    });
        } else
            Log.d("1", "Network not avaialble");
    }

    private void loadFromCache() {

        Log.d("1", "Loading from cache");
        Realm realm = Realm.getInstance(this);
        RealmResults<RealmMovie> query = realm.where(RealmMovie.class)
                .findAll();
        if (query.size() < 1)
            return;

        for (RealmMovie realmMovie : query) {
            Movie movie = new Movie();
            movie.setTitle(realmMovie.getTitle());
            movie.setRelease_date(realmMovie.getRelease_date());
            movie.setPopularity(realmMovie.getPopularity());
            movie.setPoster_path(realmMovie.getPoster_path());
            movieRecyclerAdapter.addData(movie);
        }
        movieRecyclerAdapter.notifyDataSetChanged();
    }

    private void clearCache() {
        Log.d("1", "Removing cache");
        Realm realm = Realm.getInstance(getApplicationContext());
        realm.beginTransaction();
        RealmResults<RealmMovie> query = realm.where(RealmMovie.class)
                .findAll();
        if (query.size() > 0)
            realm.clear(RealmMovie.class);
        realm.commitTransaction();
    }


    public boolean isNetConnected() {
        Log.d("1", "Checking Network connection");
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();

    }

    public void addToDB(Movie movie) {
        Realm realm = Realm.getInstance(getApplicationContext());
        realm.beginTransaction();
        RealmMovie p = realm.createObject(RealmMovie.class);
        p.setTitle(movie.getTitle());
        p.setRelease_date(movie.getRelease_date());
        p.setPopularity(movie.getPopularity());
        if (movie.getPoster_path() == null)
            p.setPoster_path("");
        else
            p.setPoster_path(movie.getPoster_path());

        if (movie.getBackdropPath() == null)
            p.setBackdropPath("");
        else
            p.setBackdropPath(movie.getBackdropPath());

        p.setRelease_date(movie.getRelease_date());
        p.setVote_average(movie.getVote_average());
        p.setId(movie.getId());

        realm.commitTransaction();
    }
}
