package com.example.nhem.eventwithlove.event.activities.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alorma.timeline.RoundTimelineView;
import com.alorma.timeline.TimelineView;
import com.example.nhem.eventwithlove.R;
import com.example.nhem.eventwithlove.event.activities.Adapter.TimelineAdapter;
import com.example.nhem.eventwithlove.event.activities.Preferences;
import com.example.nhem.eventwithlove.event.activities.models.domain.Timeline;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimelineFragment extends Fragment {

    String event;
    List<Timeline> listTimeline = new ArrayList<>();

    public TimelineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);
//        setupUI(view);
//        if (!Preferences.getInstance().getEvent().equals("")){
            event = Preferences.getInstance().getEvent();
//        }
        getData(view);

        return view;
    }

    private void getData(final View view) {
        Log.d("TimelineFragment", "getData: " + event);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("event").child(event).child("process");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listTimeline.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Timeline timeline = snapshot.getValue(Timeline.class);
                    listTimeline.add(timeline);
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setupUI(view);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void setupUI(View view) {
        buildSamples(view);
    }

    @SuppressLint("StringFormatInvalid")
    private void buildSamples(View view) {
        RecyclerView list = view.findViewById(R.id.list);

        ArrayList<Timeline> items = new ArrayList<>();
        items.add(new Timeline("Bắt đầu", TimelineView.TYPE_START));
        for (int i = 0; i < listTimeline.size(); i++) {
            Date date = new Date(listTimeline.get(i).getStart());
            DateFormat format = new SimpleDateFormat("hh:mm");
            String time = format.format(date);
            String content = time + "     " + listTimeline.get(i).getName();
            items.add(new Timeline(content,
                    TimelineView.TYPE_MIDDLE));
        }
        items.add(new Timeline("Kết thúc", TimelineView.TYPE_END));

        list.setLayoutManager(new LinearLayoutManager(view.getContext()));
        TimelineAdapter adapter = new TimelineAdapter(LayoutInflater.from(view.getContext()), items);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
