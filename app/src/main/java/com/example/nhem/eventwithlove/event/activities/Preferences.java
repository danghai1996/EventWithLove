package com.example.nhem.eventwithlove.event.activities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by NHEM on 20/01/2018.
 */

public class Preferences {
    private static final String KEY = "Event";
    private static final String TOKEN_KEY = "TOKEN";
    private static final String EVENT_KEY = "ID_EVENT";

    private SharedPreferences sharedPreferences;

    public Preferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
    }

    public void putToken(String token) {
        this.sharedPreferences.edit().putString(TOKEN_KEY, token).commit();
    }

    public String getToken() {
        return this.sharedPreferences.getString(TOKEN_KEY, "");
    }

    public void putEvent(String event) {
        this.sharedPreferences.edit().putString(EVENT_KEY, event).commit();
    }

    public String getEvent() {
        return this.sharedPreferences.getString(EVENT_KEY, "");
    }

    private static Preferences instance;

    public static Preferences getInstance() {
        return instance;
    }

    public static void init(Context context) {
        instance = new Preferences(context);
    }

}
