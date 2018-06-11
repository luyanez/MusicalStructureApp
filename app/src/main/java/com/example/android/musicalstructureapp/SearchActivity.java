package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final ImageView img_playlist_icon_search;
        final ImageView img_music_icon_search;

        img_playlist_icon_search = (ImageView) findViewById(R.id.playlist_icon);
        img_music_icon_search = (ImageView) findViewById(R.id.music_icon);

        img_playlist_icon_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playlist_intent = new Intent(SearchActivity.this, MainActivity.class);

                //Start Numbers Activity
                startActivity(playlist_intent);

                //finish();

            }
        });

        img_music_icon_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent music_intent = new Intent(SearchActivity.this, MusicActivity.class);

                //Start Numbers Activity
                startActivity(music_intent);

                //finish();

            }
        });

    }

}
