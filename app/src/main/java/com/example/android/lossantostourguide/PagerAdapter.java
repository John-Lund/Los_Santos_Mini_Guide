package com.example.android.lossantostourguide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    private String[] mTabNames;

    public PagerAdapter(FragmentManager fm, String[] tabNames) {
        super(fm);
        this.mTabNames = tabNames;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PlacesFragment();
        }
        if (position == 1) {
            return new ActivitiesFragment();
        }
        if (position == 2) {
            return new PropertyFragment();
        }
        if (position == 3) {
            return new CustomisationFragment();
        } else {
            return new TransportFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabNames[position];
    }

    @Override
    public int getCount() {
        return 5;
    }
}
