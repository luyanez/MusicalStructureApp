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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    ArrayList<Song> songs = new ArrayList<Song>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final LinearLayout img_playlist_icon_search;
        final LinearLayout img_music_icon_search;
        final ImageView search_button;

        //Get all songs from Device
        getAllSongs();

        img_playlist_icon_search = (LinearLayout) findViewById(R.id.playlist_section);
        img_music_icon_search = (LinearLayout) findViewById(R.id.music_section);

        search_button = (ImageView)findViewById(R.id.search_button);

        //Create OnClickListener to open Playlist(Main) Activity
        img_playlist_icon_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playlist_intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(playlist_intent);
                finish();

            }
        });

        //Create OnClickListener to open Music Activity
        img_music_icon_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent music_intent = new Intent(SearchActivity.this, MusicActivity.class);
                startActivity(music_intent);
                finish();

            }
        });

        //Create Search_button OnClickListener to search for the Songs.
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchSong();

            }
        });

    }

    /*
       Get all Songs from Device
     */
    private void getAllSongs(){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            readDataExternal();
        }

    }

    /* Search the input of the EditText through the songs, it is going to search
       in the name, artist and album Strings. Then it will create a list with the songs
       that are valid to that condition and it will print them using an Adapter.
     */
    private void searchSong()
    {
        EditText ideEditText = (EditText) findViewById(R.id.name_text_input);
        String searchText = ideEditText.getText().toString().trim();
        ArrayList<Song> songToPrint;

        if(!searchText.isEmpty()) {

            songToPrint = searchSongsList(searchText);

            if (!songToPrint.isEmpty()) {
                SongAdapter adapter = new SongAdapter(this, songToPrint);

                ListView listView = (ListView) findViewById(R.id.Music_list);
                listView.setAdapter(adapter);
            }
        }

    }

    //Helper function to search the String on the songs List.
    private ArrayList<Song> searchSongsList(String searchText)
    {
        ArrayList<Song> newList = new ArrayList<Song>();
        String name;
        String artist;
        String album;
        String minutes;
        String seconds;
        searchText = searchText.toLowerCase();

        for (int i = 0; i < songs.size(); i++) {
            name = songs.get(i).getSongName();
            artist = songs.get(i).getSongAuthor();
            album = songs.get(i).getAlbum();

            if (name.toLowerCase().contains(searchText) || artist.toLowerCase().contains(searchText) || album.toLowerCase().contains(searchText))
            {
                minutes = songs.get(i).getMinutes();
                seconds = songs.get(i).getSeconds();
                newList.add(new Song(name,artist,minutes,seconds,album));
            }
        }
        return newList;
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

        if(musicCursor!=null && musicCursor.moveToFirst()){
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
                long seconds = time/1000;
                long minutes = seconds/60;
                seconds = seconds % 60;

                songs.add(new Song(thisTitle,thisArtist,Long.toString(minutes),Long.toString(seconds),thisAlbum));

                //Log.d("luceroTag", thisTitle+" - "+thisArtist+" : "+thisAlbum+" , "+Long.toString(minutes)+":"+Long.toString(seconds));
            }
            while (musicCursor.moveToNext());

        }
    }


}
