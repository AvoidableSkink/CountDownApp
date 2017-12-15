package com.example.klind.countdownapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by klind on 12/13/2017.
 */

public class AddEventActivity extends AppCompatActivity {

    public static final int DATA_REQUEST = 1001;
    static final int DIALOG_ID = 0;
    int mYear,mMonth,mDay;
    String mTitle,mDate,mImage;
    Date todaysDate;

    TextView displayDate,displayImage;
    EditText eventTitle;
    Button setDate,addEvent;
    SimpleDateFormat dateFormat;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Intent intent = getIntent();
        mImage = intent.getStringExtra(ChooseImageActivity.ITEM_KEY);

        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        //TODO: you are working on letting the user choose their image!!!!
        eventTitle = (EditText) findViewById(R.id.enter_event_title);
        setDate = (Button) findViewById(R.id.setDate);
        addEvent = (Button) findViewById(R.id.addEvent);
        displayImage = (TextView) findViewById(R.id.txt_image);

        displayDate = (TextView) findViewById(R.id.date_display);
        mDate = Integer.toString(mYear)+"-"+Integer.toString(mMonth+1)+"-"+Integer.toString(mDay);
        displayDate.setText(mDate);
        displayImage.setText((mImage));

         dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        try {
            todaysDate = dateFormat.parse(mDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
                addTheEvent();
            }
        });
    }

    private void addTheEvent(){
        //if they don't enter a title it reads in "" and you need to tell them to enter a title
        mTitle = eventTitle.getText().toString();

        //if the user hasnt entered a title they are asked to do so
        if (mTitle.equals(""))
        {
            Toast.makeText(AddEventActivity.this,"Please enter a title for your event",Toast.LENGTH_LONG).show();
            return;
        }

        // a toast message that just displays the title
        Toast.makeText(AddEventActivity.this,mTitle,Toast.LENGTH_LONG).show();
        done();
    }

    private void done()
    {
        //give back the title,date, and image
        //already have the title set from when they pressed add event
        String date = displayDate.getText().toString();
        String image = displayImage.getText().toString();

        Intent intent = new Intent();
        intent.putExtra(MainActivity.TITLE_KEY,mTitle);
        intent.putExtra(MainActivity.DATE_KEY,date);
        intent.putExtra(MainActivity.IMG_KEY,image);

        setResult(RESULT_OK,intent);
        finish();
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

            mDate = Integer.toString(mYear)+"-"+Integer.toString(mMonth)+"-"+Integer.toString(mDay);

            //check that the chosen date is not in the future
            //if it is, increment the year by one
            try {
                Date chosenDate = dateFormat.parse(mDate);
                if(chosenDate.getTime() <= todaysDate.getTime())
                {
                    mYear++;
                    mDate = Integer.toString(mYear)+"-"+Integer.toString(mMonth)+"-"+Integer.toString(mDay);
                    Toast.makeText(AddEventActivity.this,"Date has already passed, event date set to next year.", Toast.LENGTH_LONG).show();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            displayDate.setText(mDate);
        }
    };

}
