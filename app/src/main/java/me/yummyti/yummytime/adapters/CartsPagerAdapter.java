package me.yummyti.yummytime.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import me.yummyti.yummytime.fragments.AllIngredientsFragment;
import me.yummyti.yummytime.fragments.RecipesFragment;

public class CartsPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public CartsPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                RecipesFragment tab1 = new RecipesFragment();
                return tab1;
            case 1:
                AllIngredientsFragment tab2 = new AllIngredientsFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}