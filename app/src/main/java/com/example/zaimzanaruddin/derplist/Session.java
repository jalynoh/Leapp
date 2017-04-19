package com.example.zaimzanaruddin.derplist;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by PUOR on 4/11/17.
 */

//SESSIONS ARE USED FOR PERSISTENCE AS AN ALTERNATIVE TO SQLITE (ALSO CALLED SHARED PREFERENCES)
public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public Session(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("leapp", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setCurrentUser(String name) { //sets current logged in user
        editor.putString("currentUser", name);
        editor.commit();
    }

    public String getCurrentUser() { //gets current logged in user
        return prefs.getString("currentUser", "none");
    }

    public void setLoggedin(boolean loggedin) {
        editor.putBoolean("loggedInmode", loggedin);
        editor.commit();
    }

    public void setUser_name(String name){
        editor.putString("user_name", name);
        editor.commit();

    }

    public String getUser_name(){return prefs.getString("user_name", "none");}

    public void setUser_Email(String name){
        editor.putString("user_Email", name);
        editor.commit();

    }
    public String getUser_Email(){return prefs.getString("user_Email", "none");}


    public void setUser_Password(String name){
        editor.putString("user_Password", name);
        editor.commit();

    }

    public String getUser_Password(){return prefs.getString("user_Password", "none");}








    public boolean loggedin() {
        return prefs.getBoolean("loggedInmode", false);
    }
}
