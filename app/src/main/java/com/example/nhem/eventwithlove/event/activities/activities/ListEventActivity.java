package com.example.nhem.eventwithlove.event.activities.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhem.eventwithlove.R;
import com.example.nhem.eventwithlove.event.activities.Adapter.ListEventAdapter;
import com.example.nhem.eventwithlove.event.activities.events.LoadingBeginEvent;
import com.example.nhem.eventwithlove.event.activities.events.LoadingBeginEvent1;
import com.example.nhem.eventwithlove.event.activities.events.LoadingEndEvent;
import com.example.nhem.eventwithlove.event.activities.events.LoadingEndEvent1;
import com.example.nhem.eventwithlove.event.activities.fragment.LoadingFragment;
import com.example.nhem.eventwithlove.event.activities.models.domain.Event;
import com.example.nhem.eventwithlove.event.activities.receivers.DataEventReceiver;
import com.example.nhem.eventwithlove.event.activities.services.GetDataEventService;
import com.example.nhem.eventwithlove.event.activities.services.LoginService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class ListEventActivity extends AppCompatActivity{
    public static final String getDataFromFirebaseSuccess = "getDataFromFirebaseSuccess";
    private static final String TAG = ListEventActivity.class.toString();
    ImageView ivQRcode;
    ImageView ivBack;
    ListView lvEvent;
    AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event);

        setupUI();

        getListData();


        ivQRcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListEventActivity.this, ScannerCodeActivity.class);
                startActivity(intent);
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void getListData() {
        Log.d(TAG, "getListData: ");
        EventBus.getDefault().post(new LoadingBeginEvent());
        final List<Event> list = new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("event");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Event event = snapshot.getValue(Event.class);
                    list.add(event);
                    Log.d(TAG, "onDataChange: " + event.toString());
                }
                EventBus.getDefault().post(new LoadingEndEvent());
                ListEventAdapter adapter = new ListEventAdapter(list, ListEventActivity.this);
                lvEvent.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });
    }

    private void setupUI() {
        ivQRcode = findViewById(R.id.iv_qrcode);
        ivBack = findViewById(R.id.iv_back);
        lvEvent = findViewById(R.id.lv_event);
        avi = findViewById(R.id.avi1);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onMessageEvent(LoadingBeginEvent event) {
        avi.show();
    }

    @Subscribe
    public void onMessageEvent(LoadingEndEvent event) {
        avi.hide();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
        System.exit(0);
    }
}
