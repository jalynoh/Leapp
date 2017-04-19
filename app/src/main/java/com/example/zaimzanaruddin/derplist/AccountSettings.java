package com.example.zaimzanaruddin.derplist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.zaimzanaruddin.derplist.ui.ListActivity;

public class AccountSettings extends AppCompatActivity {

    private TextView TV_Name;
    private TextView TV_Email;
    private Session session;
    private Button BTN_Change;
    private Button BTN_Gb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        session = new Session(this);
        if(!session.loggedin()) { //if user was not previously logged in, logout
            logout();
        }

        TV_Email=(TextView)findViewById(R.id.TV_Email);
        TV_Name=(TextView)findViewById(R.id.TV_Name);
        TV_Email.setText(session.getCurrentUser());
        TV_Name.setText(session.getUser_name());
        BTN_Change = (Button)findViewById(R.id.BTN_Change);
        BTN_Change.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(AccountSettings.this, ChangePassword.class));
            } });
        BTN_Gb = (Button)findViewById(R.id.BTN_Gb);
        BTN_Gb.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(AccountSettings.this, ListActivity.class));
            } });

        //BTN_Change.setOnClickListener(this);
        }



    private void logout() {
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void onClick(View v) {
        //below code is used to check which button has been clicked
        switch(v.getId()) {
            case R.id.BTN_Change:
                startActivity(new Intent(AccountSettings.this, ChangePassword.class)); //jump to LoginActivity
                finish();
                break;
            case R.id.BTN_Gb:
                startActivity(new Intent(AccountSettings.this, ListActivity.class));
                break;

            default:
        }
    }
}
