package com.example.klind.countdownapp;

import android.content.Context;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by klind on 12/14/2017.
 */

public class backgroundAdapter extends RecyclerView.Adapter<backgroundAdapter.BGHolder> {

    private List<Background> mItems;
    private Context context;
    private SharedPreferences.OnSharedPreferenceChangeListener prefsListener;

    public backgroundAdapter(Context context, List<Background> items) {
        this.context = context;
        this.mItems = items;
    }

    @Override
    public backgroundAdapter.BGHolder onCreateViewHolder(ViewGroup parent, int viewType) {

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
        int layoutId = grid ? R.layout.grid_item : R.layout.list_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(layoutId, parent, false);
        backgroundAdapter.BGHolder bgHolder = new BGHolder(itemView);
        return bgHolder;
    }


    @Override
    public void onBindViewHolder(backgroundAdapter.BGHolder holder, int position) {
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

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, CountdownActivity.class);
//                intent.putExtra(ITEM_KEY, item);
//                mContext.startActivity(intent);
                Toast.makeText(context,"clicked a photo",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class BGHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView tvImageName;
        public View mView;

        public BGHolder(View itemView) {
            super(itemView);
            tvImageName = (TextView) itemView.findViewById(R.id.bg_name);
            imageView = (ImageView) itemView.findViewById(R.id.bg_item);
            mView = itemView;
        }
    }
}
