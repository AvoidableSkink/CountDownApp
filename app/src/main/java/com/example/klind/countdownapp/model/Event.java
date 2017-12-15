package com.example.klind.countdownapp.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.klind.countdownapp.database.EventsTable;

import java.util.UUID;

/**
 * Created by klind on 12/13/2017.
 */

public class Event implements Parcelable {

    private String eventId;
    private String eventName;
    private String eventDate;
    private int sortPosition;
    private String image;

    public Event() {
    }

    public Event(String eventId, String eventName, String eventDate, int sortPosition, String image) {

        if (eventId == null) {
            eventId = UUID.randomUUID().toString();
        }

        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.sortPosition = sortPosition;
        this.image = image;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {return eventDate;}

    public void setEventDate(String eventDate) {this.eventDate = eventDate;}

    public int getSortPosition() {
        return sortPosition;
    }

    public void setSortPosition(int sortPosition) {
        this.sortPosition = sortPosition;
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

        values.put(EventsTable.COLUMN_ID,eventId);
        values.put(EventsTable.COLUMN_NAME,eventName);
        values.put(EventsTable.COLUMN_POSITION,sortPosition);
        values.put(EventsTable.COLUMN_IMAGE,image);

        return values;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId='" + eventId + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", sortPosition=" + sortPosition +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.eventId);
        dest.writeString(this.eventName);
        dest.writeString(this.eventDate);
        dest.writeInt(this.sortPosition);
        dest.writeString(this.image);
    }

    protected Event(Parcel in) {
        this.eventId = in.readString();
        this.eventName = in.readString();
        this.eventDate = in.readString();
        this.sortPosition = in.readInt();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel source) {
            return new Event(source);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}
