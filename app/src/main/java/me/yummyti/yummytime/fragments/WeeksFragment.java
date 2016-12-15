package me.yummyti.yummytime.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yummyti.yummytime.R;


public class WeeksFragment extends Fragment {
    public static final String ARGS_INSTANCE = "me.yummyti.yummytime.argsInstance";

    BaseFragment.FragmentNavigation mFragmentNavigation;
    int mInt = 0;

    public static WeeksFragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        WeeksFragment fragment = new WeeksFragment();
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
        View view = inflater.inflate(R.layout.fragment_week, container, false);

        /*WeekService.getWeek("22", new WeekService.WeeksListener() {
            @Override
            public void onFindSpots(ArrayList<Spots> spots) {

                createMap(spots);

                Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFail() {
                Toast.makeText(MainActivity.this, "no", Toast.LENGTH_SHORT).show();
            }
        });*/

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
