package com.example.zaimzanaruddin.derplist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zaimzanaruddin.derplist.ui.ListActivity;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login, register;
    private EditText ET_Email, ET_Password;
    private dbHelper db;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new dbHelper(this);
        session = new Session(this);
        login = (Button)findViewById(R.id.BTN_Login);
        register = (Button)findViewById(R.id.BTN_Register);
        ET_Email = (EditText)findViewById(R.id.ET_Email);
        ET_Password = (EditText)findViewById(R.id.ET_Password);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

        //checks if user exited the app logged in, if so skip the login page
        if(session.loggedin()) {
            startActivity(new Intent(LoginActivity.this, ListActivity.class));
            finish();
        }
    }

    //pre-made android class
    @Override
    public void onClick(View v) {
        //below code is used to check which button has been clicked
        switch(v.getId()) {
            case R.id.BTN_Login:
                login();
                break;
            case R.id.BTN_Register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class)); //jump to RegisterActivity
                break;
            default:
        }
    }

    private void login() {
        String email = ET_Email.getText().toString();
        String pass = ET_Password.getText().toString();
        //String name = db.getName(email);

        if(db.verifyUser(email, pass)) { //if user is validated
            session.setLoggedin(true); //set session to true
            session.setCurrentUser(email); //sets current user

            startActivity(new Intent(LoginActivity.this, ListActivity.class)); //jump to MainActivity
            finish();
        }
        else { //displays error message
            Toast.makeText(getApplicationContext(), "Wrong email/password", Toast.LENGTH_SHORT).show();
        }
    }
}
