package com.example.zaimzanaruddin.derplist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button BTN_Register;
    private TextView TV_Login;
    private EditText ET_Name, ET_Email, ET_Username, ET_Password;
    private dbHelper db;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        session = new Session(this);
        db = new dbHelper(this);
        BTN_Register = (Button)findViewById(R.id.BTN_Register);
        TV_Login = (TextView)findViewById(R.id.TV_Login);
        ET_Name = (EditText)findViewById(R.id.ET_Name);
        ET_Email = (EditText)findViewById(R.id.ET_Email);
        ET_Username = (EditText)findViewById(R.id.ET_UserName);
        ET_Password = (EditText)findViewById(R.id.ET_Password);

        BTN_Register.setOnClickListener(this);
        TV_Login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //below code is used to check which button has been clicked
        switch(v.getId()) {
            case R.id.BTN_Register:
                register();
                break;
            case R.id.TV_Login:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class)); //jump back to LoginActivity
                finish();
                break;
            default:
        }
    }

    private void register() {
        String name = ET_Name.getText().toString();
        String email = ET_Email.getText().toString();
        String username = ET_Username.getText().toString();
        String password = ET_Password.getText().toString();

        if(name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            displayToast("Incomplete"); //displays error message
        }
        else if(email.contains("@") == false) {
            displayToast("Email invalid");
        }
        else if(password.length() < 4) {
            displayToast("Password must be at least 4 characters");
        }
        else if(db.isDuplicateUser(username)) {
            displayToast("Username already taken");
        }
        else {
            db.addUser(name, email, username, password); //stores in db
            session.setUser_name(name);
            displayToast("User registered"); //displays confirmation message
            finish();
        }
    }



    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
