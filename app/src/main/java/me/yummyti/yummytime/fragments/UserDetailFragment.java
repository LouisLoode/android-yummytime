package me.yummyti.yummytime.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yummyti.yummytime.ApplicationController;
import me.yummyti.yummytime.R;
import me.yummyti.yummytime.models.RecipeLabels;
import me.yummyti.yummytime.models.User;

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link UserDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserDetailFragment extends Fragment {

    @BindView(R.id.detail_user_imageview)
    protected NetworkImageView networkImageView;

    @BindView(R.id.detail_user_name_textview)
    protected TextView userNameTextview;

    @BindView(R.id.detail_user_description_textview)
    protected TextView userDescriptionTextview;

    private User user;

    public UserDetailFragment() {
        // Required empty public constructor
    }

    public static UserDetailFragment newInstance(User user) {

        UserDetailFragment userDetailFragment = new UserDetailFragment();

        // Not good at all
        userDetailFragment.user = user;


        return userDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user_detail, container, false);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        ImageLoader imageLoader = ApplicationController.getInstance()
                .getImageLoader();

        userNameTextview.setText(user.getName());
        //userDescriptionTextview.setText(user.getDescription());


    }
    

}