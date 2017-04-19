package com.example.zaimzanaruddin.derplist.model;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.zaimzanaruddin.derplist.Event;
import com.example.zaimzanaruddin.derplist.R;

/**
 * Created by zaimzanaruddin on 4/16/17.
 */

public class DerpData {
    private static final String[] locations = {"Nedderman",
            "University Center ",
            "ERB",
            "Central Library",
            "College Park"

    };
    private static final String[] titles = {"UTA Campus Connect",
            "Pizza with the President",
            "Khallil's Stock class",
            "MSA Block Party",
            "Auns Need help Coding"};


    private static final String[] date = {"test1",
            "test2",
            "test3",
            "test4",
            "test5"};


    private static final String[] time = {"3455",
            "5936",
            "4935",
            "5932",
            "4953"};


    private static final int icon = R.drawable.ic_event_black_36dp;
/*
    public static List<ListItem> getListData() {
        List<ListItem> data = new ArrayList<>();

        //Repeat process 4 times, so that we have enough data to demonstrate a scrollable
        //RecyclerView
        for (int x = 0; x < 4; x++) {
            //create ListItem with dummy data, then add them to our List
            for (int i = 0; i < titles.length; i++) {
                ListItem item = new ListItem();
                item.setTitle(titles[i]);
                item.setlocation(locations[i]);
                item.setDate(date[i]);
                item.setTime(time[i]);
                data.add(item);
            }
        }
        return data;
    }
*/

    public static List<ListItem> getListData() {
        List<ListItem> data = new ArrayList<>();
        int count = 0;

        for (Event e : Event.Event_List) {
            ListItem item = new ListItem();
            item.setTitle(e.getEvent_Title());
            item.setlocation(e.getEvent_Location());
            item.setDate(e.getEvent_Start());
            item.setImageResId(e.getEvent_Image());
            data.add(item);
            count++;



        }
        return data;
    }

}






