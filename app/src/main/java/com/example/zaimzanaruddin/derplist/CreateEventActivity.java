package com.example.zaimzanaruddin.derplist;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zaimzanaruddin.derplist.ui.ListActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;

public class CreateEventActivity extends AppCompatActivity implements View.OnClickListener {
    private Button BTN_Create, BTN_InsertImage;
    private ImageView IV_Image;
    private TextView TV_Exit;
    private EditText ET_Date, ET_Location, ET_Description, ET_Start_Time, ET_EventName;
    private dbHelper db;
    private static final int SELECT_PHOTO = 100;
    //public String imagePath;
    public Uri selectedImageUri;
    private static final int PERMS_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        db = new dbHelper(this);
        IV_Image = (ImageView)findViewById(R.id.IV_Image);
        BTN_InsertImage = (Button)findViewById(R.id.BTN_InsertImage);
        BTN_Create = (Button)findViewById(R.id.BTN_Create);
        ET_EventName = (EditText)findViewById(R.id.ET_EventName);
        ET_Date = (EditText)findViewById(R.id.ET_Date);
        ET_Start_Time = (EditText)findViewById(R.id.ET_Start_Time);
        ET_Location = (EditText)findViewById(R.id.ET_Location);
        ET_Description = (EditText)findViewById(R.id.ET_Description);
        TV_Exit = (TextView)findViewById(R.id.TV_Exit);

        BTN_InsertImage.setOnClickListener(this);
        BTN_Create.setOnClickListener(this);
        TV_Exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.BTN_InsertImage:
                openGallery();
                break;
            case R.id.BTN_Create:
                addNewEvent();
                break;
            case R.id.TV_Exit:
                startActivity(new Intent(CreateEventActivity.this, ListActivity.class)); //jump back to MainActivity
                finish();
                break;
            default:
        }
    }

    public void addDefaultEvents() {
       String[] locations = {"Nedderman",
                "University Center ",
                "ERB",
                "College Park District",
                "College Park",
               "ERB",
               "Pie Five",
               "MAC",
               "Pickard Hall"

        };

        String[] titles = {"UTA Campus Connect",
                "Pizza with the President",
                "Khallil's Stock class",
                "MSA Block Party",
                "Auns Need help Coding",
                "Birthday Party",
                "Free Massages",
                "Tshirt Exchange",
                "Glass Show"

        };



        String[] description = {"UTA Campus Connect",
                "Pizza with the President",
                "Khallil's Stock class",
                "MSA Block Party",
                "Auns Need help Coding",
                "There will be an Event",
                "Events Will Be here",
                "Please Give us an A",
                "Dont break the App"};



        String[] start = {"3:00",
                "12:00",
                "1:30",
                "10:02",
                "7:75",
                "2:45",
               "6:41",
               "5:00",
                "3:21"};


        int[] likes = {34,
                59,
                10,
                5,
                100,
                78,
                 56,
                 28,
        2};

        String[] imageURI = {
                "android.resource://com.example.zaimzanaruddin.derplist/drawable/utanedd",
                "android.resource://com.example.zaimzanaruddin.derplist/drawable/utauc",
                "android.resource://com.example.zaimzanaruddin.derplist/drawable/utak",
                "android.resource://com.example.zaimzanaruddin.derplist/drawable/utav",
                "android.resource://com.example.zaimzanaruddin.derplist/drawable/utamavericks",
                "android.resource://com.example.zaimzanaruddin.derplist/drawable/utaerb2",
                "android.resource://com.example.zaimzanaruddin.derplist/drawable/utaedge",
                "android.resource://com.example.zaimzanaruddin.derplist/drawable/utamac",
                "android.resource://com.example.zaimzanaruddin.derplist/drawable/utaerb"
        };



        for(int i = 0; i<8; i++)
        {
            Event e = new Event(start[i], titles[i], locations[i], description[i], likes[i], false, imageURI[i]);
            Event.Event_List.add(e);
        }

    }

    @TargetApi(11)
    public void addNewEvent(){
        String title = ET_EventName.getText().toString();
        String date = ET_Date.getText().toString();
        String location = ET_Location.getText().toString();
        String description = ET_Description.getText().toString();
        String start = ET_Start_Time.getText().toString();
        String imageUri = selectedImageUri.toString();


        Event e = new Event(start, title, location, description, 0, false, imageUri);
        Event.Event_List.add(e);

        // Add Event to database
        if(title.isEmpty() || date.isEmpty() || location.isEmpty() || description.isEmpty() || start.isEmpty()) {
            displayToast("Field left empty"); //displays error message
        }
        else {
            db.addEvent(title, date, start, location, description,0, false, imageUri); //stores in db
            displayToast("event created"); //displays confirmation message
            startActivity(new Intent(CreateEventActivity.this, ListActivity.class)); //jump back to MainActivity
            finish();
        }
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void openGallery() { //opens the android gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PHOTO); //starts finder activity followed by onActivityResult()
    }

    //displays selected image into imageView (continuation of startActivityResult())
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PHOTO) {
                // Get the url from data
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Get the path from the Uri
                    String imagePath = getPathFromURI(selectedImageUri);
                    // Set the image in ImageView

                    IV_Image.setImageURI(selectedImageUri);

                    displayToast(selectedImageUri +": SELECTED");
                }
            }
        }
    }

    /* Get the real path from the URI */
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }


}