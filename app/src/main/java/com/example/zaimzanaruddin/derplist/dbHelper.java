package com.example.zaimzanaruddin.derplist;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

/**
 * Created by PUOR on 4/11/17.
 */


public class dbHelper extends SQLiteOpenHelper {
    //default database naming settings
    public static final String DB_NAME = "leapp.db";
    public static final int DB_VERSION = 2; //number of tables in the db

    //below are the fields for the table "users"
    public static final String USER_TABLE = "users";
    public static final String COLUMN_IDUSER = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    /*
    SQL VERSION OF THE BELOW CODE
    create table users(
            id integer primary key autoincrement,
            email text,
            password text)
     */
    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + USER_TABLE + "("
            + COLUMN_IDUSER + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_USERNAME + " TEXT,"
            + COLUMN_PASSWORD + " TEXT);";



    //below are the field for the table "events"
    public static final String EVENT_TABLE = "events";
    public static final String COLUMN_IDEVENT = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_START = "start";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LIKES = "likes";
    public static final String COLUMN_ACTIVE = "active";
    public static final String COLUMN_IMAGE = "image";

    public static final String CREATE_TABLE_EVENTS = "CREATE TABLE " + EVENT_TABLE + " ("
            + COLUMN_IDEVENT + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_TITLE + " TEXT,"
            + COLUMN_DATE + " TEXT,"
            + COLUMN_START + " TEXT,"
            + COLUMN_LOCATION + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_LIKES + " TEXT,"
            + COLUMN_ACTIVE + " TEXT,"
            + COLUMN_IMAGE + " TEXT);";



    public dbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //pre-made android studio class
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_EVENTS);
    }

    //pre-made android studio class
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EVENT_TABLE);
        onCreate(db);
    }

    //adds user details to db table "users"
    public void addUser(String name, String email, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        //email and password must be stored in the
        //values var. in the corresponding column name
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);

        //now the values var. can be inserted into the "users" table
        long result = db.insert(USER_TABLE, null, values); //result error checks for correct insertion, change to boolean
        db.close();
    }

    //validates login credentials on login activity
    public boolean verifyUser(String email, String password) {
        String selectQuery = "select * from " + USER_TABLE + " where " +
                COLUMN_EMAIL + " = " + "'"+email+"'" + " and " + COLUMN_PASSWORD+ " = " + "'"+password+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null); //used as a row pointer for the table
        cursor.moveToFirst(); //move to first row of table
        if(cursor.getCount() > 0) { //if the email/password matches a row in the table
            return true;
        }
        cursor.close();
        db.close();

        return false; //if the email/password does not match a row in the table
    }

    //adds new event to "events" table ADD byte[] image !!!!!!
    public void addEvent(String title, String date, String start, String location, String description, int likes, boolean active, String image) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_LOCATION, location);
        values.put(COLUMN_START, start);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_LIKES, likes);
        values.put(COLUMN_ACTIVE, active);
        values.put(COLUMN_IMAGE, image); //test

        long result = db.insert(EVENT_TABLE, null, values);
        db.close();
    }

    //return data from event table to populate list view in MainActivity
    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + EVENT_TABLE;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    //returns only the ID that matches the name passed
    public Cursor getItemID(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_IDEVENT + " FROM " + EVENT_TABLE +
                " WHERE " + COLUMN_TITLE + " = " + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    // Creates the Arraylist
    public static void Populate_Event_List(dbHelper db)
    {
        long id;
        String start;
        String title;
        String location;
        String description;
        int likes;
        boolean active;

        Cursor data = db.getData(); //get data and appends it to list
        while(data.moveToNext()) {
            id = data.getLong(1);
            start = data.getString(2);
            title = data.getString(3);
            location = data.getString(4);
            description = data.getString(5);
            likes = data.getInt(6);
            active = data.getInt(7) > 0;

            Event e = new Event(id, start, title, location, description, likes, active);
            Event.Event_List.add(e);
        }
    }

    // Change to Username implementation after Aun fixes login
    public String getName(String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_NAME + " FROM " + USER_TABLE +
                " WHERE " + COLUMN_EMAIL + " = " + email + "'";
        Cursor data = db.rawQuery(query, null);

        String name = data.getString(1);
        return name;
    };
    
    public boolean isDuplicateUser(String username) {
        String selectQuery = "select * from " + USER_TABLE + " where " +
                COLUMN_USERNAME + " = " + "'"+username+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            return true;
        }

        cursor.close();
        db.close();

        return false;
    }

    // Username implementation
    /*public String getName(String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_NAME + " FROM " + USER_TABLE +
                " WHERE " + COLUMN_USERNAME + " = " + username + "'";
        Cursor data = db.rawQuery(query, null);

        String name = data.getString(1);
        return name;
    };*/


}
