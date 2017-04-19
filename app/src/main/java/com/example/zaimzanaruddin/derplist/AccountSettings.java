package com.example.zaimzanaruddin.derplist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.zaimzanaruddin.derplist.ui.ListActivity;

public class AccountSettings extends AppCompatActivity {

    private TextView TV_Name;
    private TextView TV_Email;
    private Session session;

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
    }

    private void logout() {
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }
}
