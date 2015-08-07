package com.nuron.flickz.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nuron.flickz.MovieDB.Movie;
import com.nuron.flickz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunil on 03-Aug-15.
 */
public class MovieRecyclerAdapter  extends RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder> {
    List<Movie> mItems;
    Context context;

    public MovieRecyclerAdapter(Context context1) {
        super();
        context = context1;
        mItems = new ArrayList<>();
    }

    public void addData(Movie movie) {
        mItems.add(movie);
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Movie movie = mItems.get(i);

        viewHolder.login.setText(movie.getTitle());
        viewHolder.repos.setText("Release Date : " + movie.getReleaseDate());
        viewHolder.blog.setText("Rating : " + movie.getVoteAverage());
//        Picasso.with(context)
//                .load("https://image.tmdb.org/t/p/w185" + movie.getPosterPath())
//                .into(viewHolder.poster);

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w185" + movie.getPosterPath())
                .into(viewHolder.poster);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView login;
        public TextView repos;
        public TextView blog;
        public ImageView poster;


        public ViewHolder(View itemView) {
            super(itemView);
            login = (TextView) itemView.findViewById(R.id.login);
            repos = (TextView) itemView.findViewById(R.id.repos);
            blog = (TextView) itemView.findViewById(R.id.blog);
            poster = (ImageView) itemView.findViewById(R.id.poster);
        }
    }
}