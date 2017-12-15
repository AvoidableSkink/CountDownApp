package com.example.klind.countdownapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.klind.countdownapp.model.Event;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by klind on 12/13/2017.
 */

public class AddEventActivity extends AppCompatActivity {

    static final int DIALOG_ID = 0;
    int mYear,mMonth,mDay;
    String mTitle,mDate,mImage;

    TextView displayDate;
    EditText eventTitle;
    Button setDate,addEvent;
    //null name date sortposition picture

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        eventTitle = (EditText) findViewById(R.id.enter_event_title);
        setDate = (Button) findViewById(R.id.setDate);
        addEvent = (Button) findViewById(R.id.addEvent);

        displayDate = (TextView) findViewById(R.id.date_display);
        mDate = Integer.toString(mYear)+"-"+Integer.toString(mMonth+1)+"-"+Integer.toString(mDay);
        displayDate.setText(mDate);

        setListeners();
    }

    public void setListeners(){
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_ID);
            }
        });

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if they don't enter a title it reads in "" and you need to tell them to enter a title
                String theTitle = eventTitle.getText().toString();
                Toast.makeText(AddEventActivity.this,theTitle,Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if (DIALOG_ID == id){
            return new DatePickerDialog(this, datePickerListener,mYear,mMonth,mDay);
        }
        else return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            mYear = year;
            mMonth = month + 1;
            mDay = day;

            mDate = Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(day);
            displayDate.setText(mDate);
            Toast.makeText(AddEventActivity.this,mDate, Toast.LENGTH_LONG).show();
        }
    };

}
