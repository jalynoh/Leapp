package com.example.zaimzanaruddin.derplist.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import java.util.ArrayList;
import java.util.List;
import android.view.Menu;

import android.content.Intent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.zaimzanaruddin.derplist.AccountSettings;
import com.example.zaimzanaruddin.derplist.CreateEventActivity;
import com.example.zaimzanaruddin.derplist.Event;
import com.example.zaimzanaruddin.derplist.LoginActivity;
import com.example.zaimzanaruddin.derplist.AccountSettings;
import com.example.zaimzanaruddin.derplist.MainActivity;
import com.example.zaimzanaruddin.derplist.R;
import com.example.zaimzanaruddin.derplist.RegisterActivity;
import com.example.zaimzanaruddin.derplist.Session;
import com.example.zaimzanaruddin.derplist.SplashScreen;
import com.example.zaimzanaruddin.derplist.adapter.DerpAdapter;
import com.example.zaimzanaruddin.derplist.model.DerpData;
import com.example.zaimzanaruddin.derplist.model.ListItem;
import com.example.zaimzanaruddin.derplist.Filter;

public class ListActivity extends AppCompatActivity implements DerpAdapter.ItemClickCallback {


    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_TITLE = "EXTRA_TITLE";
    private static final String EXTRA_LOC = "EXTRA_LOC";
    private static final String EXTRA_DES = "EXTRA_DES";
    private static final String EXTRA_TIME = "EXTRA_TIME";
    private static final String EXTRA_PIC = "EXTRA_PIC";


    private RecyclerView recView;
    private DerpAdapter adapter;
    private ArrayList listData;
    private Session session;
    private boolean upvoted=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        session = new Session(this);
        if (!session.loggedin()) { //if user was not previously logged in, logout
            logout();
        }

        listData = (ArrayList) DerpData.getListData();

        recView = (RecyclerView) findViewById(R.id.rec_list);
        //Check out GridLayoutManager and StaggeredGridLayoutManager for more options
        recView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new DerpAdapter(DerpData.getListData(), this);
        recView.setAdapter(adapter);
        adapter.setItemClickCallback(this);
    }

    private void logout() {
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(ListActivity.this, LoginActivity.class));
    }


    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater mflater = getMenuInflater();
        mflater.inflate(R.menu.mymenu, menu);
        //inflater.inflate(R.menu.refresh,menu);
        //inflater.inflate(R.menu.stop,menu);
        //return super.onCreateOptionsMenu(menu);


        menu.getItem(0).setIcon(R.drawable.event);
        //menu.getItem(1).setIcon(R.drawable.out);
        menu.getItem(2).setIcon(R.drawable.filter);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Event:
                startActivity(new Intent(this, CreateEventActivity.class));
                return true;
            case R.id.SignOff:
                logout();
                finish();
                return true;
            case R.id.Filter:
                startActivity(new Intent(this,Filter.class));
                return true;
            case R.id.Setting:
                startActivity(new Intent(ListActivity.this, AccountSettings.class));
                return true;
            default:
                return true;

        }
    }


    @Override
    public void onItemClick(int p){
        ListItem item = (ListItem) listData.get(p);

        Intent i = new Intent(this, DetailActivity.class);

        Bundle extras = new Bundle();
        extras.putString(EXTRA_TITLE, item.getTitle());
        extras.putString(EXTRA_LOC, item.getlocation());
        extras.putString(EXTRA_DES, item.getDescription());
        extras.putString(EXTRA_TIME, item.getTime());
        extras.putString(EXTRA_PIC, item.getImageResId());

        i.putExtra(BUNDLE_EXTRAS, extras);

        startActivity(i);
    }

    @Override
    public void onSecondaryIconClick(int p) {

        ListItem item = (ListItem) listData.get(p);

       if(upvoted==false)
       {
           item.upVote();
           upvoted=true;
       }






    //pass new data to adapter and update
    adapter.setListData(listData);
    adapter.notifyDataSetChanged();


    }


}