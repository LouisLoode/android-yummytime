package me.yummyti.yummytime.adapters;

/**
 * Created by louisloode on 12/12/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import me.yummyti.yummytime.fragments.CartFragment;
import me.yummyti.yummytime.fragments.DiscoverFragment;
import me.yummyti.yummytime.fragments.WeekFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new DiscoverFragment();
                return fragment;
            case 1:
                fragment = new CartFragment();
                return fragment;
            case 2:
                fragment = new WeekFragment();
                return fragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}