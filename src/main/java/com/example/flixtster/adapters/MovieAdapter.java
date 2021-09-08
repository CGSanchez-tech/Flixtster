package com.example.flixtster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixtster.DetailActivity;
import com.example.flixtster.R;
import com.example.flixtster.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Viewholder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movie) {
        this.context = context;
        this.movies = movie;
    }
    //usually involves inflating a layout from XML (item_movie) and returning the holder
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new Viewholder(movieView);
    }
    //Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder" + position );
        //get the movie at the passed in position
        Movie movie = movies.get(position);
        //bind the movie data into the VH
        holder.bind(movie);
    }
    //returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        //viewholder is is a representation of our in the recycler view
        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);

        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl;
            //if phone in landscape
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                //then imageUrl = backdrop image
                imageUrl = movie.getBackdropPath();
            } else {
                //else imageUrl = poster image
                imageUrl = movie.getPosterPath();
            }
            Glide.with(context).load(imageUrl).into(ivPoster);

            //first, register click listener on the whole row
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //second, navigate to anew activity on tap
                    Intent i = new Intent(context, DetailActivity.class);
                    context.startActivity(i);
                }
            });
        }
    }

}
