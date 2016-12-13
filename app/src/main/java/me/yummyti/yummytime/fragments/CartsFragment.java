package me.yummyti.yummytime.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yummyti.yummytime.R;


public class CartsFragment extends android.support.v4.app.Fragment {
    public static final String ARGS_INSTANCE = "me.yummyti.yummytime.argsInstance";

    BaseFragment.FragmentNavigation mFragmentNavigation;
    int mInt = 0;

    public static CartsFragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        CartsFragment fragment = new CartsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mInt = args.getInt(ARGS_INSTANCE);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseFragment.FragmentNavigation) {
            mFragmentNavigation = (BaseFragment.FragmentNavigation) context;
        }
    }


}
