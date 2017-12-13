package com.example.klind.countdownapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.klind.countdownapp.model.Event;

/**
 * Created by klind on 12/13/2017.
 */

public class DataSource {
    private Context mContext;
    private SQLiteDatabase mdatabase;
    private SQLiteOpenHelper mdbHelper;

    public DataSource(Context Context) {
        this.mContext = Context;
        mdbHelper = new DBHelper(mContext);
        mdatabase = mdbHelper.getWritableDatabase();
    }

    public void open(){
        mdatabase = mdbHelper.getWritableDatabase();
    }
    public void close(){
        mdbHelper.close();
    }

    public Event createItem(Event event)
    {
        ContentValues values = event.toValues();
        mdatabase.insert(EventsTable.TABLE_EVENTS,null,values);
        return event;
    }

    public long getEventsCount()
    {
        return DatabaseUtils.queryNumEntries(mdatabase,EventsTable.TABLE_EVENTS);
    }
}
