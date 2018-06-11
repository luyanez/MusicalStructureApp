package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView img_playlist_icon;
        final ImageView img_music_icon;
        final ImageView img_search_icon;

        img_music_icon = (ImageView) findViewById(R.id.music_icon);
        img_search_icon = (ImageView) findViewById(R.id.search_icon);

        img_music_icon.setImageResource(R.drawable.music);
        img_search_icon.setImageResource(R.drawable.search);

        img_music_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent music_intent = new Intent(MainActivity.this, MusicActivity.class);

                //Start Numbers Activity
                startActivity(music_intent);

                finish();

            }
        });

        img_search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search_intent = new Intent(MainActivity.this, SearchActivity.class);

                //Start Numbers Activity
                startActivity(search_intent);

                finish();

            }
        });


        ArrayList<Playlist> playlist = new ArrayList<Playlist>();
        playlist.add(new Playlist("All Songs"));
        playlist.add(new Playlist("Trova Songs"));
        playlist.add(new Playlist("Singing in the shower"));
        playlist.add(new Playlist("Driving songs"));

        PlayListAdapter adapter = new PlayListAdapter(this, playlist);

        GridView gridView = (GridView) findViewById(R.id.Playlist_list);

        gridView.setAdapter(adapter);


    }
}
