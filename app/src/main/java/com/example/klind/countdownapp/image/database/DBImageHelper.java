package com.example.klind.countdownapp.image.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.klind.countdownapp.database.EventsTable;
import com.example.klind.countdownapp.model.Background;

/**
 * Created by klind on 12/13/2017.
 */

public class DBImageHelper extends SQLiteOpenHelper {

    public static final String DB_File_Name = "backgrounds.db";
    public static final int DB_VERSION = 1;

    public DBImageHelper(Context context) {
        super(context, DB_File_Name, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BackgroundsTable.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(BackgroundsTable.SQL_DELETE);
        onCreate(db);
    }
}
