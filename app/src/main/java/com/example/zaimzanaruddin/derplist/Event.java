package com.example.zaimzanaruddin.derplist;

//import java.util.Date;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.util.ArrayList;

public class Event {
    public static ArrayList<Event> Event_List = new ArrayList<Event>();

    private long Event_ID;              // Used to reference Events
    private String Event_Start;         // Time the Event starts
    private String Event_Title;         // Title of the Event
    private String Event_Location;      // Location of the Event
    private String Event_Description;   // Description of the Event
    private int Event_Likes;            // Number of likes the event has
    private boolean Event_Active;       // Tells whether the Event is expired or not
    private String Event_Image;         // Holds the URL for the event image


    public Event(String start, String title, String location, String description, int likes, boolean active, String image) {

        Event_ID = System.currentTimeMillis();
        Event_Start = start;
        Event_Title = title;
        Event_Location = location;
        Event_Description = description;
        Event_Likes = likes;
        Event_Active = active;
        Event_Image = image;
    }

    public Event(long id, String start, String title, String location, String description, int likes, boolean active) {

        Event_ID = id;
        Event_Start = start;
        Event_Title = title;
        Event_Location = location;
        Event_Description = description;
        Event_Likes = likes;
        Event_Active = active;
    }


    public String getEvent_Start() {
        return Event_Start;
    }

    public void setEvent_Start(String Event_Start) {
        Event_Start = Event_Start;
    }

    public String getEvent_Title() {
        return Event_Title;
    }

    public void setEvent_Title(String event_Title) {
        Event_Title = event_Title;
    }

    public String getEvent_Location() {
        return Event_Location;
    }

    public void setEvent_Location(String event_Location) {
        Event_Location = event_Location;
    }

    public String getEvent_Description() {
        return Event_Description;
    }

    public void setEvent_Description(String event_Description) {
        Event_Description = event_Description;
    }

    public int getEvent_Likes() {
        return Event_Likes;
    }

    public void setEvent_Likes(int event_Likes) {
        Event_Likes = event_Likes;
    }

    public boolean isEvent_Active() {
        return Event_Active;
    }

    public void setEvent_Active(boolean event_Active) {
        Event_Active = event_Active;
    }

    public long getEvent_ID() {
        return Event_ID;
    }

    public void setEvent_ID(long event_ID) {
        Event_ID = event_ID;
    }

    public String getEvent_Image() {
        return Event_Image;
    }

    public void setEvent_Image(String event_Image) {
        Event_Image = event_Image;
    }

    public void Like_Event() {
        this.Event_Likes++;
    }

    public void Dislike()
    {
        this.Event_Likes--;
    }

    public void Expire_Event()
    {
        Event_Active = false;
    }

}
