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

    public boolean loggedin() {
        return prefs.getBoolean("loggedInmode", false);
    }
}
