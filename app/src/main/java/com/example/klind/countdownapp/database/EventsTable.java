package com.example.klind.countdownapp.database;

/**
 * Created by klind on 12/13/2017.
 */

public class EventsTable {
    public static final String TABLE_EVENTS = "events";
    public static final String COLUMN_ID = "eventId";
    public static final String COLUMN_NAME = "eventName";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_POSITION = "sortPosition";
    public static final String COLUMN_IMAGE = "image";
    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_EVENTS + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_DATE + " TEXT," +
                    COLUMN_POSITION + " INTEGER," +
                    COLUMN_IMAGE + " TEXT" + ");";
    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_EVENTS;
}