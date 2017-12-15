package com.example.klind.countdownapp.image.database;

/**
 * Created by klind on 12/13/2017.
 */

public class BackgroundsTable {
    public static final String TABLE_BACKGROUNDS = "backgrounds";
    public static final String COLUMN_ID = "eventId";
    public static final String COLUMN_IMAGE = "image";
    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_BACKGROUNDS + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    COLUMN_IMAGE + " TEXT" + ");";
    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_BACKGROUNDS;
}