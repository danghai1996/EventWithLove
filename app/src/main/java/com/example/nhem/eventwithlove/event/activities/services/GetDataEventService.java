package com.example.nhem.eventwithlove.event.activities.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.nhem.eventwithlove.event.activities.activities.MainActivity;
import com.example.nhem.eventwithlove.event.activities.models.domain.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hau on 1/20/2018.
 */

public class GetDataEventService extends IntentService {
    private static final String TAG = GetDataEventService.class.toString();

    public GetDataEventService(String name) {
        super("GetDataEventService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onHandleIntent(@Nullable final Intent intent) {
        Log.d(TAG, "onHandleIntent: ");
        final List<Event> list = new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("event");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Event event = snapshot.getValue(Event.class);
                    list.add(event);
                    Log.d(TAG, "onDataChange: " + event.toString());
                }
                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction(MainActivity.mBroadcastLoginSuccessAction);
                Bundle bundle = new Bundle();
                bundle.putParcelable("listEvent", (Parcelable) list);
                broadcastIntent.putExtras(bundle);
                sendBroadcast(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });

    }

    @Override
    public boolean stopService(Intent name) {
        stopSelf();
        return super.stopService(name);
    }
}
