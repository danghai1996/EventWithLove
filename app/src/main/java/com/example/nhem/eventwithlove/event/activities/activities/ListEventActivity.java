package com.example.nhem.eventwithlove.event.activities.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
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

import java.util.ArrayList;
import java.util.List;

public class ListEventActivity extends AppCompatActivity implements DataEventReceiver.Receiver{
    public static final String getDataFromFirebaseSuccess = "getDataFromFirebaseSuccess";
    private static final String TAG = ListEventActivity.class.toString();
    ImageView ivQRcode;
    ImageView ivBack;
    ListView lvEvent;
    List<Event> list;

    private IntentFilter mIntentFilter;
    public DataEventReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event);

        mReceiver = new DataEventReceiver(new Handler());
        mReceiver.setReceiver(this);
        setupUI();

//        final List<Event> list = getListData();
        getListData();


        ivQRcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListEventActivity.this, QRActvity.class);
                startActivity(intent);
//                finish();
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
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(getDataFromFirebaseSuccess);
        registerReceiver(mReceiver, mIntentFilter);
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
            mReceiver = null;
        }
        super.onPause();
    }

    private void getListData() {
        Log.d(TAG, "getListData: ");
        Intent intent = new Intent(this, GetDataEventService.class);
        startService(intent);
        Fragment loadingFragment = new LoadingFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentLoading, loadingFragment);
        transaction.commit();
    }

    private void setupUI() {
        ivQRcode = findViewById(R.id.iv_qrcode);
        ivBack = findViewById(R.id.iv_back);
        lvEvent = findViewById(R.id.lv_event);
    }

//    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.d(TAG, "onReceive: ");
//            if (intent.getAction().equals(getDataFromFirebaseSuccess)) {
//                getFragmentManager().popBackStack();
//                Bundle bundle = intent.getExtras();
//                list = bundle.getParcelable("listEvent");
//                lvEvent.setAdapter(new ListEventAdapter(list, ListEventActivity.this));
//                Intent stopIntent = new Intent(ListEventActivity.this, GetDataEventService.class);
//                stopService(stopIntent);
//            }
//        }
//    };

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        Log.d(TAG, "onReceiveResult: ");
    }
}
