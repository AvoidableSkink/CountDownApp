package com.example.klind.countdownapp.image.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.klind.countdownapp.database.DBHelper;
import com.example.klind.countdownapp.database.EventsTable;
import com.example.klind.countdownapp.model.Background;
import com.example.klind.countdownapp.model.Event;

/**
 * Created by klind on 12/13/2017.
 */

public class DataImageSource {
    private Context mContext;
    private SQLiteDatabase mdatabase;
    private SQLiteOpenHelper mdbHelper;

    public DataImageSource(Context Context) {
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

    public Background createItem(Background background)
    {
        ContentValues values = background.toValues();
        mdatabase.insert(BackgroundsTable.TABLE_BACKGROUNDS,null,values);
        return background;
    }

    public long getBackgroundsCount()
    {
        return DatabaseUtils.queryNumEntries(mdatabase,BackgroundsTable.TABLE_BACKGROUNDS);
    }
}
