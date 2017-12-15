package com.example.klind.countdownapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.klind.countdownapp.database.DataSource;
import com.example.klind.countdownapp.model.Event;
import com.example.klind.countdownapp.sample.SampleDataProvider;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.example.klind.countdownapp.sample.SampleDataProvider.eventItemList;

public class MainActivity extends AppCompatActivity {

    public static final int DATA_REQUEST = 1001;
    List<Event> eventItemList = SampleDataProvider.eventItemList;
    DataSource mDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mDataSource = new DataSource(this);
        mDataSource.open();
        //Toast.makeText(this, "Database Created", Toast.LENGTH_SHORT).show();

        long numItems = mDataSource.getEventsCount();
//        if (numItems == 0) {
//            for (Event item : eventItemList) {
//                try {
//                    mDataSource.createItem(item);
//                } catch (SQLiteException e) {
//                    e.printStackTrace();
//                }
//            }
//            //Toast.makeText(this, "Data Inserted",
//              //      Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            //Toast.makeText(this, "Data already present",
//              //      Toast.LENGTH_SHORT).show();
//        }
//        Collections.sort(eventItemList, new Comparator<Event>() {
//            @Override
//            public int compare(Event o1, Event o2) {
//                return o1.getEventName().compareTo(o2.getEventName());
//            }
//        });

        EventAdapter adapter = new EventAdapter(this, eventItemList);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        boolean grid = settings.getBoolean(getString(R.string.pref_display_grid), false);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        if (grid) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddEventActivity.class);
                startActivityForResult(intent,DATA_REQUEST);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DATA_REQUEST && resultCode == RESULT_OK){
            //TODO: create and add the new event returned using the data returned in the intent
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDataSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataSource.open();
    }
}
