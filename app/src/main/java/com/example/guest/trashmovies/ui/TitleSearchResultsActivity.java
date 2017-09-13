package com.example.guest.trashmovies.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    @Bind(R.id.titleTextView) TextView mTitleTextView;
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
                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        Log.v(TAG, jsonData);
                        mMovies = movieService.processResults(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
