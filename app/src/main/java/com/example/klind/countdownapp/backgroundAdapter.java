package com.example.klind.countdownapp;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.klind.countdownapp.model.Event;

import java.util.List;

/**
 * Created by klind on 12/14/2017.
 */

public class backgroundAdapter extends RecyclerView.Adapter<backgroundAdapter.BGHolder> {

    private List<backgroundAdapter> mItems;

    @Override
    public BGHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BGHolder holder, int position) {

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
