package com.example.android.musicalstructureapp;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {

    private ArrayList<Song> songs = new ArrayList<Song>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        final LinearLayout img_playlist_icon_music;
        final LinearLayout img_search_icon_music;

        img_playlist_icon_music = (LinearLayout) findViewById(R.id.playlist_section);
        img_search_icon_music = (LinearLayout) findViewById(R.id.search_section);

        //Add OnClickListener to open Playlist(Main) Activity
        img_playlist_icon_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playlist_intent = new Intent(MusicActivity.this, MainActivity.class);
                startActivity(playlist_intent);
                finish();

            }
        });

        //Add OnClickListener to open Search Activity
        img_search_icon_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search_intent = new Intent(MusicActivity.this, SearchActivity.class);
                startActivity(search_intent);
                finish();

            }
        });

        //Add permission variables in order to read the songs from the phone
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            readDataExternal();
        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    readDataExternal();
                }
                break;

            default:
                break;
        }
    }

    private void readDataExternal()
    {
        ContentResolver musicResolver = getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);

        /* Check if songs list is empty, if so, read the songs from the memory of the phone and add
           each song to the songs list. Each element will contain name, artist, album and duration of the song.
           This information will be used to fill the listView usign an Adapter.
         */
        if (songs.isEmpty()){

            if(musicCursor!=null && musicCursor.moveToFirst()) {
                //get columns
                int titleColumn = musicCursor.getColumnIndex
                        (android.provider.MediaStore.Audio.Media.TITLE);
                int idColumn = musicCursor.getColumnIndex
                        (android.provider.MediaStore.Audio.Media._ID);
                int artistColumn = musicCursor.getColumnIndex
                        (android.provider.MediaStore.Audio.Media.ARTIST);

                int albumColumn = musicCursor.getColumnIndex
                        (MediaStore.Audio.Media.ALBUM);

                int durationColumn = musicCursor.getColumnIndex
                        (MediaStore.Audio.Media.DURATION);

                //add songs to list
                do {
                    long thisId = musicCursor.getLong(idColumn);
                    String thisTitle = musicCursor.getString(titleColumn);
                    String thisArtist = musicCursor.getString(artistColumn);
                    String thisAlbum = musicCursor.getString((albumColumn));
                    long thisDuration = musicCursor.getLong(durationColumn);

                    long time = Long.valueOf(thisDuration);
                    long seconds = time / 1000;
                    long minutes = seconds / 60;
                    seconds = seconds % 60;

                    songs.add(new Song(thisTitle, thisArtist, Long.toString(minutes), Long.toString(seconds), thisAlbum));

                    //Log.d("luceroTag", thisTitle+" - "+thisArtist+" : "+thisAlbum+" , "+Long.toString(minutes)+":"+Long.toString(seconds));
                }
                while (musicCursor.moveToNext());
            }

            SongAdapter adapter = new SongAdapter(this, songs);

            ListView listView = (ListView) findViewById(R.id.Music_list);
            listView.setAdapter(adapter);
        }
    }

}



