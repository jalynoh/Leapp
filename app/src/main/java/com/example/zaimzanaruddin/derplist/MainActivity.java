package com.example.zaimzanaruddin.derplist;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button BTN_Logout, BTN_CreateEvent;
    private Session session;
    private ListView LV_List;
    private dbHelper db;


    //pre-made android studio class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new Session(this);
        if(!session.loggedin()) { //if user was not previously logged in, logout
            logout();
        }


        BTN_Logout = (Button)findViewById(R.id.BTN_Logout);
        BTN_Logout.setText(session.getCurrentUser()); //sets logout button text to current user !!!!
        BTN_CreateEvent = (Button)findViewById(R.id.BTN_CreateEvent);
        LV_List = (ListView)findViewById(R.id.LV_List);
        BTN_CreateEvent.setOnClickListener(this);
        BTN_Logout.setOnClickListener(this);
        db = new dbHelper(this);

        populateListView();
    }

    private void populateListView() {
        /*Cursor data = db.getData(); //get data and appends it to list
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()) {
            listData.add(data.getString(1)); //get data column 1 of event table (COLUMN_TITLE) and add it to arraylist
        }
        */
        ArrayList<String> listData = new ArrayList<>();
        for(Event e: Event.Event_List)
        {
            listData.add(e.getEvent_Title());
        }
        //create and set a list adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        LV_List.setAdapter(adapter);


        LV_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();

                Cursor data = db.getItemID(name); //return ID to corresponding name
                int itemID = -1; //Error check to see if it is returning data that exists
                while(data.moveToNext()) {
                    itemID = data.getInt(0);
                }
                if(itemID > -1) {
                    displayToast("good ID");
                    //New intent statement for in-depth event
                }
                else {
                    displayToast("No ID");
                }
            }
        });
    }
    //pre-made android studio class
    @Override
    public void onClick(View v) {
        //below code is used to check which button has been clicked
        switch(v.getId()) {
            case R.id.BTN_Logout:
                logout();
                startActivity(new Intent(MainActivity.this, LoginActivity.class)); //jump to LoginActivity
                finish();
                break;
            case R.id.BTN_CreateEvent:
                startActivity(new Intent(MainActivity.this, CreateEventActivity.class));
                finish();
                break;
            default:
        }
    }

    private void logout() {
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
