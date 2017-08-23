package com.example.android.mediaplayer.Adapters;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.android.mediaplayer.Models.Song;
import com.example.android.mediaplayer.R;
import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter(Activity context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        final Song currentSong = getItem(position);
        TextView defaultLanguageTextView = (TextView) listItemView.findViewById(R.id.textView1);
        defaultLanguageTextView.setText(currentSong.getTitle());
        TextView miwokLanguageTextView = (TextView) listItemView.findViewById(R.id.textView2);
        miwokLanguageTextView.setText(currentSong.getBand());
        return listItemView;
    }
}
