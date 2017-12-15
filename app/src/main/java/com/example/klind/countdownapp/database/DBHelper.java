package com.example.klind.countdownapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.klind.countdownapp.model.Event;

import java.util.ArrayList;

/**
 * Created by klind on 12/13/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String COLUMN_ID = "eventID";
    public static final String DB_File_Name = "events.db";
    public static final int DB_VERSION = 1;
    public static final String COLUMN_NAME = "eventName";

    public DBHelper(Context context) {
        super(context, DB_File_Name, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EventsTable.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(EventsTable.SQL_DELETE);
        onCreate(db);
    }

    public ArrayList<Event> getAllEvents() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Event> listItems = new ArrayList<Event>();

        Cursor cursor = db.rawQuery("SELECT * from " + "events",
                new String[] {});

        if (cursor.moveToFirst()) {
            do {
                Event event = new Event();

                event.setEventId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));

                event.setEventName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));

                listItems.add(event);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return listItems;
    }

    public void deleteEvent(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String string = String.valueOf(id);
        db.execSQL("DELETE FROM " + EventsTable.TABLE_EVENTS + " WHERE " + EventsTable.COLUMN_ID
                + "=" + id + "");
    }
}
