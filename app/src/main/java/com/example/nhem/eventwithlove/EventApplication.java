package com.example.nhem.eventwithlove;

import android.app.Application;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.example.nhem.eventwithlove.event.activities.Preferences;

/**
 * Created by Hau on 1/20/2018.
 */

public class EventApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Preferences.init(this);

    }
}
