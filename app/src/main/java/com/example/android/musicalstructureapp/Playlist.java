package com.example.android.musicalstructureapp;

import java.util.ArrayList;

/**
 * Created by Lucero Yanez on 6/10/18.
 * This Playlist class has the name and the songs of each Playlist.
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
