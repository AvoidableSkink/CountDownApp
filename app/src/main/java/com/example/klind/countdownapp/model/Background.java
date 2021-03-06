package com.example.klind.countdownapp.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.klind.countdownapp.database.EventsTable;
import com.example.klind.countdownapp.image.database.BackgroundsTable;

import java.util.UUID;

/**
 * Created by klind on 12/14/2017.
 */

public class Background implements Parcelable {

    public String image;
    public String backgroundId;

    public Background(){}

    public Background(String backgroundId, String image) {

        if (backgroundId == null) {
            backgroundId = UUID.randomUUID().toString();
        }

        this.backgroundId = backgroundId;
        this.image = image;
    }

    public String getBackgroundID() {
        return backgroundId;
    }

    public void setBackgroundId(String backgroundId) {
        this.backgroundId = backgroundId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ContentValues toValues()
    {
        ContentValues values = new ContentValues(7);

        values.put(BackgroundsTable.COLUMN_ID,backgroundId);
        values.put(EventsTable.COLUMN_IMAGE,image);

        return values;
    }

    @Override
    public String toString() {
        return "Background{" +
                "backgroundId='" + backgroundId + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.backgroundId);
        dest.writeString(this.image);
    }

    protected Background(Parcel in) {
        this.backgroundId = in.readString();
        this.image = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Background> CREATOR = new Parcelable.Creator<Background>() {
        @Override
        public Background createFromParcel(Parcel source) {
            return new Background(source);
        }

        @Override
        public Background[] newArray(int size) {
            return new Background[size];
        }
    };
}
