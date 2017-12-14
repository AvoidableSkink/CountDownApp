package com.example.klind.countdownapp;

import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.time.LocalDateTime;

/**
 * Created by klind on 12/13/2017.
 */

public class AddEventActivity implements DatePickerDialog.OnDateSetListener {

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        addEventViewModel.setEventDateTime(LocalDateTime.of(year, month + 1, dayOfMonth, 0, 0));
//        textViewCurrentDate.setText(addEventViewModel.getEventDateTime().toString());
    }
}
