package com.example.guest.trashmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TitleSearchResultsActivity extends AppCompatActivity {

    Intent oldIntent = getIntent();
    final String title = oldIntent.getStringExtra("title");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_search_results);
    }
}
