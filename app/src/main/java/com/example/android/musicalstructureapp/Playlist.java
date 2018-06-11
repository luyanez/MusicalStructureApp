package com.example.android.musicalstructureapp;

import java.util.ArrayList;

/**
 * Created by macbook on 6/10/18.
 */

public class Playlist {
    public String namePlaylist;
    public ArrayList<Song> songs;

    public Playlist(String name)
    {
        this.namePlaylist = name;
        this.songs = new ArrayList<Song>();
    }

    public String getNamePlaylist()
    {
        return this.namePlaylist;
    }

}
