package com.example.nhem.eventwithlove.event.activities.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.nhem.eventwithlove.event.activities.fragment.QuestionFragment;
import com.example.nhem.eventwithlove.event.activities.fragment.TimelineFragment;
import com.example.nhem.eventwithlove.event.activities.fragment.VoteFragment;

/**
 * Created by NHEM on 20/01/2018.
 */

public class EventAdapter extends FragmentStatePagerAdapter {


    public EventAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TimelineFragment();
            case 1:
                return new QuestionFragment();
            case 2:
                return new VoteFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
