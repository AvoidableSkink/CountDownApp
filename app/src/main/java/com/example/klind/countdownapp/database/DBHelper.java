package com.example.klind.countdownapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by klind on 12/13/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_File_Name = "events.db";
    public static final int DB_VERSION = 1;

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
}
