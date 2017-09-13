package com.example.guest.trashmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.buttonTitle) Button mButtonTitle;
    @Bind(R.id.titleEditText) EditText mTitleEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mButtonTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mTitleEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, TitleSearchResultsActivity.class);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });

    }
}
