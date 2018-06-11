package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        final ImageView img_playlist_icon_music;
        final ImageView img_search_icon_music;

        img_playlist_icon_music = (ImageView) findViewById(R.id.playlist_icon);
        img_search_icon_music = (ImageView) findViewById(R.id.search_icon);

        img_playlist_icon_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playlist_intent = new Intent(MusicActivity.this, MainActivity.class);

                //Start Numbers Activity
                startActivity(playlist_intent);

                finish();

            }
        });

        img_search_icon_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search_intent = new Intent(MusicActivity.this, SearchActivity.class);

                //Start Numbers Activity
                startActivity(search_intent);

                finish();

            }
        });

        ArrayList<Song> songs = new ArrayList<Song>();
        songs.add(new Song("Drops of Jupiter","Train","04","14","Pop","Drops of Jupiter"));
        songs.add(new Song("Whole Lotta Love", "Led Zeppelin","05","34","Rock","Led Zeppelin II"));
        songs.add(new Song("Back In Black","AC/DC","04","15","Rock","Back In Black"));
        songs.add(new Song("Hells Bells","AC/DC","05","13","Rock","Back In Black"));
        songs.add(new Song("Shoot to Thrill","AC/DC","05","17","Rock","Back In Black"));
        songs.add(new Song("Should I Stay or Should I Go","The Clash","03","08","Rock","Combat Rock"));

        SongAdapter adapter = new SongAdapter(this, songs);

        ListView gridView = (ListView) findViewById(R.id.Music_list);

        gridView.setAdapter(adapter);


    }

}
