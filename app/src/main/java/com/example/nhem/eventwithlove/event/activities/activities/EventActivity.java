package com.example.nhem.eventwithlove.event.activities.activities;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nhem.eventwithlove.R;
import com.example.nhem.eventwithlove.event.activities.Adapter.EventAdapter;

public class EventActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setupUI();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setupUI() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_timeline_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_question_answer_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_done_all_black_24dp));

        tabLayout.getTabAt(0).getIcon().setAlpha(255);
        tabLayout.getTabAt(1).getIcon().setAlpha(100);
        tabLayout.getTabAt(2).getIcon().setAlpha(100);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(255);
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(100);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        EventAdapter eventAdapter = new EventAdapter(getSupportFragmentManager());
        viewPager.setAdapter(eventAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}
