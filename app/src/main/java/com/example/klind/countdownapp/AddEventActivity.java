package com.example.klind.countdownapp;

import android.app.DatePickerDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.klind.countdownapp.model.Event;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

/**
 * Created by klind on 12/13/2017.
 */

public class AddEventActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        addEventViewModel.setEventDateTime(LocalDateTime.of(year, month + 1, dayOfMonth, 0, 0));
//        textViewCurrentDate.setText(addEventViewModel.getEventDateTime().toString());
    }
}
