package com.example.guest.trashmovies.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guest.trashmovies.R;
import com.example.guest.trashmovies.models.Movie;
import com.example.guest.trashmovies.service.MovieService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TitleSearchResultsActivity extends AppCompatActivity {
    @Bind(R.id.searchResultsTextView) TextView mTitleTextView;
    @Bind(R.id.movieListView) ListView mMovieListView;
    public static final String TAG = TitleSearchResultsActivity.class.getSimpleName();

    public ArrayList<Movie> mMovies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_search_results);
        ButterKnife.bind(this);

        Intent oldIntent = getIntent();
        final String title = oldIntent.getStringExtra("title");
        mTitleTextView.setText(title);

        getMovies(title);
    }

    private void getMovies(String title) {
        final MovieService movieService = new MovieService();
        movieService.findMovies(title, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mMovies = movieService.processResults(response);

                TitleSearchResultsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] movieNames = new String[mMovies.size()];
                        for (int i = 0; i < movieNames.length; i++){
                            movieNames[i] = mMovies.get(i).getTitle();
                        }
                        ArrayAdapter adapter = new ArrayAdapter(TitleSearchResultsActivity.this, android.R.layout
                                .simple_expandable_list_item_1, movieNames);
                        mMovieListView.setAdapter(adapter);

                        for(Movie movie : mMovies){
                            Log.d(TAG, "Title " + movie.getTitle());
                            Log.d(TAG, "PosterLink " + movie.getPosterLink());
                            Log.d(TAG, "ReleaseDate " + movie.getReleaseDate());
                        }

                    }
                });
            }
        });
    }
}
