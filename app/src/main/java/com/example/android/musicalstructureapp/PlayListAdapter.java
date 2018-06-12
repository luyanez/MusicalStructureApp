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

import java.util.ArrayList;

/**
 * Created by Lucero Yanez on 6/10/18.
 * Playlist Adapter that helps print the playlist object into the GridView.
 * It contains one TextView and one ImageView.
 */

public class PlayListAdapter extends ArrayAdapter<Playlist>{

    public PlayListAdapter(Activity context, ArrayList<Playlist> playlistName)
    {
        super(context, 0, playlistName);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.playlist_view, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Playlist currentPlaylist = getItem(position);

        TextView playlistName = (TextView) listItemView.findViewById(R.id.playlist_text_view);
        playlistName.setText(currentPlaylist.getNamePlaylist());

        ImageView iconView = (ImageView) listItemView.findViewById(R.id.playlist_image_view);
        iconView.setImageResource(R.drawable.playlist_icon);

        return listItemView;
    }
}
