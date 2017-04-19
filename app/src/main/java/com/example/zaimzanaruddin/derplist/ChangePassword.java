package com.example.zaimzanaruddin.derplist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {

    private Session session;
    private Button BTN_pw;
    private dbHelper db;
    private EditText ET_Newpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        session = new Session(this);
        if(!session.loggedin()) { //if user was not previously logged in, logout
            logout();
        }
        session = new Session(this);
        db = new dbHelper(this);

        BTN_pw = (Button)findViewById(R.id.BTN_pw);
        BTN_pw.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                startActivity(new Intent(ChangePassword.this, AccountSettings.class));
                Toast.makeText(getBaseContext(), "Your password has changed!" , Toast.LENGTH_SHORT ).show();
            } });
    }

    private void logout() {
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }


    private void changepw(){
        String pw = ET_Newpw.getText().toString();
        if(pw.isEmpty()){
            displayToast("Incomplete");
        }

    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }



    public void onClick(View v) {
        //below code is used to check which button has been clicked
        switch(v.getId()) {
            case R.id.BTN_pw:

                startActivity(new Intent(ChangePassword.this, AccountSettings.class));
                finish();

                break;
            default:
        }
    }



}
