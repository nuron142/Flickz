package com.nuron.flickz.Movie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nuron.flickz.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunil on 03-Aug-15.
 */
public class MovieRecyclerAdapter  extends RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder> {
    List<Result> mItems;
    Context context;

    public MovieRecyclerAdapter(Context context1) {
        super();
        context = context1;
        mItems = new ArrayList<Result>();
    }

    public void addData(Result result) {
        mItems.add(result);
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
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Result result = mItems.get(i);

        //result.get(0).
        viewHolder.login.setText(result.getTitle());
        viewHolder.repos.setText("repos: " + result.getReleaseDate());
        viewHolder.blog.setText("blog: " + result.getVoteAverage());
        Picasso.with(context)
//                .load("https://image.tmdb.org/t/p/w185"+result.getPosterPath())
                .load("https://image.tmdb.org/t/p/w185/jC1soM3OUOzehbxp7IMumgQEDvB.jpg")
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