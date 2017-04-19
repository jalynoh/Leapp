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

import com.example.zaimzanaruddin.derplist.CreateEventActivity;
import com.example.zaimzanaruddin.derplist.LoginActivity;
import com.example.zaimzanaruddin.derplist.MainActivity;
import com.example.zaimzanaruddin.derplist.R;
import com.example.zaimzanaruddin.derplist.RegisterActivity;
import com.example.zaimzanaruddin.derplist.Session;
import com.example.zaimzanaruddin.derplist.adapter.DerpAdapter;
import com.example.zaimzanaruddin.derplist.model.DerpData;
import com.example.zaimzanaruddin.derplist.model.ListItem;







public class ListActivity extends AppCompatActivity implements DerpAdapter.ItemClickCallback {
    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_QUOTE = "EXTRA_QUOTE";
    private static final String EXTRA_ATTR = "EXTRA_ATTR";

    private RecyclerView recyclerView;
    private DerpAdapter adapter;
    private ArrayList listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listData = (ArrayList) DerpData.getListData();

        recyclerView = (RecyclerView) findViewById(R.id.rec_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new DerpAdapter(DerpData.getListData(), this);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickCallback(this);
    }

    @Override
    public void onItemClick(int p) {
        ListItem item = (ListItem) listData.get(p);

        Intent i = new Intent(this, DetailActivity.class);

        Bundle extras = new Bundle();
        extras.putString(EXTRA_QUOTE, item.getTitle());
        extras.putString(EXTRA_ATTR, item.getlocation());
        i.putExtra(BUNDLE_EXTRAS, extras);

        startActivity(i);
    }

    @Override
    public void onSecondaryIconClick(int p) {

    }
}



/*
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater mflater = getMenuInflater();
        mflater.inflate(R.menu.mymenu, menu);
        //inflater.inflate(R.menu.refresh,menu);
        //inflater.inflate(R.menu.stop,menu);
        //return super.onCreateOptionsMenu(menu);
        menu.add("Settings");
        menu.add("About Us");
        menu.add("Contact ");

        menu.getItem(0).setIcon(R.drawable.event);
        menu.getItem(1).setIcon(R.drawable.out);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Event:
                startActivity(new Intent(this, CreateEventActivity.class));
                return true;
            case R.id.SignOff:
                logout();
                startActivity(new Intent(ListActivity.this, LoginActivity.class));

                finish();
                return true;
            default:
                return true;

        }
    }

*/
