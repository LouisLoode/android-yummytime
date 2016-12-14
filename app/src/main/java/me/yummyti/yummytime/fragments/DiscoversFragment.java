package me.yummyti.yummytime.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yummyti.yummytime.R;
import me.yummyti.yummytime.adapters.UserAdapter;
import me.yummyti.yummytime.models.User;
import me.yummyti.yummytime.network.UserService;


public class DiscoversFragment extends android.support.v4.app.Fragment {
    public static final String ARGS_INSTANCE = "me.yummyti.yummytime.argsInstance";
    // Log tag
    private static final String TAG = DiscoversFragment.class.getSimpleName();

    // Billionaires json url
    private static final String url = "https://raw.githubusercontent.com/mobilesiri/Android-Custom-Listview-Using-Volley/master/richman.json";
    private ProgressDialog pDialog;

    @BindView(R.id.waitingView)
    RelativeLayout waitingView;

    @BindView(R.id.usersListView)
    protected ListView usersListView;

    private UserAdapter userAdapter;

    BaseFragment.FragmentNavigation mFragmentNavigation;
    ;
    int mInt = 0;

    public static DiscoversFragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        DiscoversFragment fragment = new DiscoversFragment();
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
        View view = inflater.inflate(R.layout.fragment_discover, container, false);

        ButterKnife.bind(this, view);

        userAdapter = new UserAdapter(getContext());

        usersListView.setAdapter(userAdapter);

        showWaitingView();

        UserService.getUsers(new UserService.UsersListener() {
            @Override
            public void onReceiveUsers(User[] users) {
                userAdapter.refresh(users);
                hideWaitingView();
            }


            @Override
            public void onFailed() {
                hideWaitingView();
            }
        });
        return view;
    }


    public void onViewCreated() {

        // pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseFragment.FragmentNavigation) {
            mFragmentNavigation = (BaseFragment.FragmentNavigation) context;
        }
    }

    private void showWaitingView() {
        waitingView.setVisibility(View.VISIBLE);
    }

    private void hideWaitingView() {
        waitingView.setVisibility(View.INVISIBLE);
    }
}
