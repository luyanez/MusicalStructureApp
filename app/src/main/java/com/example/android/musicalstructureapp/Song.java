package com.example.android.musicalstructureapp;

/**
 * Created by Lucero Yanez on 6/10/18.
 * Song Class that help us to create the list of songs.
 * Each song will contain name, artist, minutes, seconds and album.
 * We define the setters and getters to easy our task to access the information.
 */

public class Song {
    private String songName;
    private String songAuthor;
    private String minutes;
    private String seconds;
    private String genres;
    private String album;

    public Song(String name, String author, String minutes, String seconds, String album)
    {
        this.songName = name;
        this.songAuthor = author;
        this.minutes = minutes;
        this.seconds = seconds;
        //this.genres = genres;
        this.album = album;
    }

    public void setSongName(String song) { this.songName = song; }

    public void setSongAuthor(String author)
    {
        this.songAuthor = author;
    }

    public void setMinutes(String minutes) { this.minutes = minutes; }

    public void setSeconds(String seconds) { this.seconds = seconds; }

    //public void setGenres(String genres){ this.genres = genres; }

    public void setAlbum(String album) { this.album = album; }

    public String getSongName()
    {
        return this.songName;
    }

    public String getSongAuthor()
    {
        return this.songAuthor;
    }

    public String getMinutes() { return this.minutes; }

    public String getSeconds() { return this.seconds; }

    //public String getGenres() { return this.genres; }

    public String getAlbum() { return this.album; }
}
