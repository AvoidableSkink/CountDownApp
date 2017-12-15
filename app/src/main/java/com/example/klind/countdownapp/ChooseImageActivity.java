package com.example.klind.countdownapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.klind.countdownapp.database.DataSource;
import com.example.klind.countdownapp.image.database.DataImageSource;
import com.example.klind.countdownapp.model.Background;
import com.example.klind.countdownapp.model.Event;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by klind on 12/14/2017.
 */

public class ChooseImageActivity extends AppCompatActivity{

    public static final String ITEM_KEY = "item_key";

    DataImageSource mDataImageSource;
    List<Background> backgroundList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image);

        mDataImageSource = new DataImageSource(this);
        mDataImageSource.open();

        long numItems = mDataImageSource.getBackgroundsCount();
            try {
                String[] images = getAssets().list("");
                int length = images.length-3;

                for (int i = 0; i < length; i++) {
                    // Get filename of file or directory
                    String filename = images[i];
                    //create a background w/ that image
                    Background bg = new Background(null,filename);
                    mDataImageSource.createItem(bg);
                    backgroundList.add(bg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        backgroundAdapter adapter = new backgroundAdapter(this, backgroundList);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean grid = settings.getBoolean(getString(R.string.pref_display_grid), false);

        RecyclerView rvChooseImage = (RecyclerView) findViewById(R.id.rv_choose_image);
        if (grid) {
            rvChooseImage.setLayoutManager(new GridLayoutManager(this, 3));
        }

        rvChooseImage.setLayoutManager(new LinearLayoutManager(this));
        rvChooseImage.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            setResult(RESULT_OK,data);
            finish();
    }
}

