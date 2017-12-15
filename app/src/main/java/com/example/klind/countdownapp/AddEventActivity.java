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

    TextView displayDate;
    EditText eventTitle;
    Button setDate,chooseImage,addEvent;
    //null name date sortposition picture

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        //TODO: you are working on letting the user choose their image!!!!
        eventTitle = (EditText) findViewById(R.id.enter_event_title);
        setDate = (Button) findViewById(R.id.setDate);
        addEvent = (Button) findViewById(R.id.addEvent);
        chooseImage = (Button) findViewById(R.id.btnChooseImage);

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
                addTheEvent();
            }
        });

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: open a new choose image activity which will be similar to the main activity
                //and use startactivityforresult();
                Intent intent = new Intent(getApplicationContext(), ChooseImageActivity.class);
                startActivityForResult(intent,DATA_REQUEST);
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

//        String firstName = textFirstName.getText().toString();
//        String lastName = textLastName.getText().toString();
//        String age = textAge.getText().toString();
//        String city = textCity.getText().toString();
//        String hobby = textHobby.getText().toString();
//        Intent intent = new Intent();
//        intent.putExtra(MainActivity.KEY_VALUE_1,firstName);
//        intent.putExtra(MainActivity.KEY_VALUE_2,lastName);
//        intent.putExtra(MainActivity.KEY_VALUE_3,age);
//        intent.putExtra(MainActivity.DATA_KEY_1,city);
//        intent.putExtra(MainActivity.DATA_KEY_2,hobby);
//
//        setResult(RESULT_OK,intent);
//        finish();

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
