package me.yummyti.yummytime.fragments;

import android.support.v4.app.Fragment;


public class BaseFragment extends Fragment {
    public static final String ARGS_INSTANCE = "com.ncapdevi.sample.argsInstance";


    public interface FragmentNavigation {
        public void pushFragment(Fragment fragment);
    }
}