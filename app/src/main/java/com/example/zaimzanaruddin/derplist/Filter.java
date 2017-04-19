package com.example.zaimzanaruddin.derplist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zaimzanaruddin.derplist.ui.ListActivity;

public class Filter extends AppCompatActivity {

    private Button BTN_GoBack;
    private Button BTN_Refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);


        BTN_GoBack = (Button)findViewById(R.id.BTN_GoBack);
        BTN_GoBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Filter.this, ListActivity.class));
            } });

        BTN_Refresh = (Button)findViewById(R.id.BTN_Refresh);
        BTN_Refresh.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Filter.this,ListActivity.class));
            } });
    }
}
