package com.example.klind.countdownapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.klind.countdownapp.model.Event;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by klind on 12/13/2017.
 */

public class EventAdapterListView extends ArrayAdapter<Event> {

    List<Event> mEvents;
    LayoutInflater mInflater;

    public EventAdapterListView(Context context, List<Event> objects) {
        super(context, R.layout.list_item, objects);

        mEvents = objects;
        mInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.itemNameText);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        Event item = mEvents.get(position);

        tvName.setText(item.getEventName());
//        imageView.setImageResource(R.drawable.apple_pie);

        InputStream inputStream = null;
        try {
            String imageFile = item.getImage();
            inputStream = getContext().getAssets().open(imageFile);
            Drawable d = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return convertView;
    }
}
