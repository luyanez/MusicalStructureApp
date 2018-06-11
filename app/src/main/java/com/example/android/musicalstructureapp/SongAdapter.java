package com.example.android.musicalstructureapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.musicalstructureapp.Playlist;
import com.example.android.musicalstructureapp.R;
import com.example.android.musicalstructureapp.Song;

import java.util.ArrayList;

/**
 * Created by macbook on 6/10/18.
 */

public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter(Activity context, ArrayList<Song> song)
    {
        super(context, 0, song);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.song_view, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Song currentSong = getItem(position);

        String name = currentSong.getSongName();
        String author = currentSong.getSongAuthor();

        String song_info = name + " - " + author;

        String minutes = currentSong.getMinutes();
        String seconds = currentSong.getSeconds();

        String song_duration = minutes + " : " + seconds;


        TextView songName = (TextView) listItemView.findViewById(R.id.song_name_view);
        songName.setText(song_info);


        TextView songDuration = (TextView) listItemView.findViewById(R.id.song_duration);
        songDuration.setText(song_duration);


        ImageView songView = (ImageView) listItemView.findViewById(R.id.song_image_view);
        songView.setImageResource(R.drawable.song_icon);

        ImageView moreView = (ImageView) listItemView.findViewById(R.id.more_image_view);
        moreView.setImageResource(R.drawable.more_icon);

        return listItemView;
    }
}
