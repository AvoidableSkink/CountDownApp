package com.example.klind.countdownapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.klind.countdownapp.model.Background;
import com.example.klind.countdownapp.model.Event;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by klind on 12/14/2017.
 */

public class backgroundAdapter extends RecyclerView.Adapter<backgroundAdapter.ViewHolder> {

    private List<Background> mItems;
    private Context context;
    private SharedPreferences.OnSharedPreferenceChangeListener prefsListener;

    public backgroundAdapter(Context context, List<Background> items) {
        this.context = context;
        this.mItems = items;
    }

    @Override
    public backgroundAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        SharedPreferences settings =
                PreferenceManager.getDefaultSharedPreferences(context);
        prefsListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                                  String key) {
                Log.i("preferences", "onSharedPreferenceChanged: " + key);
            }
        };
        settings.registerOnSharedPreferenceChangeListener(prefsListener);

        boolean grid = settings.getBoolean(
                context.getString(R.string.pref_display_grid), false);
        int layoutId = grid ? R.layout.grid_item : R.layout.bg_image_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(layoutId, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(backgroundAdapter.ViewHolder holder, int position) {
        final Background item = mItems.get(position);

        try {
            holder.tvImageName.setText(item.getImage());
            String imageFile = item.getImage();
            InputStream inputStream = context.getAssets().open(imageFile);
            Drawable d = Drawable.createFromStream(inputStream, null);
            holder.imageView.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO: FINISH THE CLICK LISTENER FOR THE IMAGES
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup parent = (ViewGroup) v;
                TextView as = (TextView) parent.findViewById(R.id.bg_name);
                String chosenImage = (String) as.getText();
                Toast.makeText(context,chosenImage,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, AddEventActivity.class);
                intent.putExtra(ChooseImageActivity.ITEM_KEY, chosenImage);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView tvImageName;
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvImageName = (TextView) itemView.findViewById(R.id.bg_name);
            imageView = (ImageView) itemView.findViewById(R.id.bg_item);
            mView = itemView;
        }
    }
}
