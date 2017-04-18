package com.example.zaimzanaruddin.derplist;

import java.util.Date;

public class Users {
    private String User_Name, User_Email, User_Username, User_Password;

    public Users(String name,  String email, String username, String password) {
        User_Name = name;
        User_Email = email;
        User_Username = username;
        User_Password = password;
    }


    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getUser_Username() {
        return User_Username;
    }

    public void setUser_Username(String user_Username) {
        User_Username = user_Username;
    }

    public String getUser_Password() {
        return User_Password;
    }

    public void setUser_Password(String user_Password) {
        User_Password = user_Password;
    }

    public String getUser_Email() {
        return User_Email;
    }

    public void setUser_Email(String user_Email) {
        User_Email = user_Email;
    }
}
