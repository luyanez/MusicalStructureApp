package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout img_music_icon;
        final LinearLayout img_search_icon;

        img_music_icon = (LinearLayout) findViewById(R.id.music_section);
        img_search_icon = (LinearLayout) findViewById(R.id.search_section);

        //Create OnClickListener for music button, to open Music Activity
        img_music_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent music_intent = new Intent(MainActivity.this, MusicActivity.class);
                startActivity(music_intent);
                finish();

            }
        });

        //Create OnClickListener for search button, to open Search Activity
        img_search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search_intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(search_intent);
                finish();

            }
        });


        /* Create temporal list that contain the information of a Playlist,
           this playlist object has the playlist name and a list of songs.
           For the moment is only example information, this playlists are not created.
         */
        ArrayList<Playlist> playlist = new ArrayList<Playlist>();
        playlist.add(new Playlist("All Songs"));
        playlist.add(new Playlist("Rock Songs"));
        playlist.add(new Playlist("Singing in the shower"));
        playlist.add(new Playlist("Driving songs"));

        //Use Adapter to print the playlist information into the gridView
        PlayListAdapter adapter = new PlayListAdapter(this, playlist);

        GridView gridView = (GridView) findViewById(R.id.Playlist_list);

        gridView.setAdapter(adapter);


    }
}
