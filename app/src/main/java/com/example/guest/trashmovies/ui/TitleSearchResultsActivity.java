package com.example.guest.trashmovies.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guest.trashmovies.R;
import com.example.guest.trashmovies.adapters.MovieListAdapter;
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
    public static final String TAG = TitleSearchResultsActivity.class.getSimpleName();
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private MovieListAdapter mAdapter;
    public ArrayList<Movie> mMovies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_search_results);
        ButterKnife.bind(this);

        Intent oldIntent = getIntent();
        final String title = oldIntent.getStringExtra("title");

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
                            mAdapter = new MovieListAdapter(getApplicationContext(), mMovies);
                            mRecyclerView.setAdapter(mAdapter);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager
                                    (TitleSearchResultsActivity.this);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setHasFixedSize(true);
                        }
                });
            }
        });
    }
}
