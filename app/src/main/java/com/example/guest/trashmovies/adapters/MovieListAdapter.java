package com.example.guest.trashmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.trashmovies.R;
import com.example.guest.trashmovies.models.Movie;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    public static final String TAG = MovieListAdapter.class.getSimpleName();
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private Context mContext;

    public MovieListAdapter(Context context, ArrayList<Movie> movies){
        mContext = context;
        mMovies = movies;
    }

    @Override
    public MovieListAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieListAdapter.MovieViewHolder holder, int position){
        holder.bindMovie(mMovies.get(position));
    }

    @Override
    public int getItemCount(){
        return mMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.movieImageView) ImageView mMovieImageView;
        @Bind(R.id.movieTitleTextView) TextView mMovieTitleTextView;
        @Bind(R.id.movieReleaseDateTextView) TextView mMovieReleaseDateTextView;

        private Context mContext;

        public MovieViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindMovie(Movie movie){
            Log.d(TAG, movie.getPosterLink());
            Picasso.with(mContext).load(movie.getPosterLink()).into(mMovieImageView);
            mMovieTitleTextView.setText(movie.getTitle());
            mMovieReleaseDateTextView.setText(movie.getReleaseDate());
        }
    }
}
