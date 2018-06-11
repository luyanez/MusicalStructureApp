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
 * Created by macbook on 6/10/18.
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

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView playlistName = (TextView) listItemView.findViewById(R.id.playlist_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        playlistName.setText(currentPlaylist.getNamePlaylist());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.playlist_image_view);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
        iconView.setImageResource(R.drawable.playlist_icon);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
