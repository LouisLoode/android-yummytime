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
import me.yummyti.yummytime.models.Cookbook;
import me.yummyti.yummytime.models.CookbookLabels;

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link CookbookDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CookbookDetailFragment extends Fragment {

    @BindView(R.id.detail_cookbook_imageview)
    protected NetworkImageView networkImageView;

    @BindView(R.id.detail_cookbook_name_textview)
    protected TextView cookbookNameTextview;

    @BindView(R.id.detail_cookbook_description_textview)
    protected TextView cookbookDescriptionTextview;

    private Cookbook cookbook;

    public CookbookDetailFragment() {
        // Required empty public constructor
    }

    public static CookbookDetailFragment newInstance(Cookbook cookbook) {

        CookbookDetailFragment cookbookDetailFragment = new CookbookDetailFragment();

        // Not good at all
        cookbookDetailFragment.cookbook = cookbook;


        return cookbookDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cookbook_detail, container, false);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        ImageLoader imageLoader = ApplicationController.getInstance()
                .getImageLoader();

        CookbookLabels cookbookLabels = cookbook.getCookbookLabels();

        if(cookbookLabels!=null) {
            String urlImageLarge = cookbookLabels.getLarge();

            networkImageView.setImageUrl(urlImageLarge, imageLoader);
        }

        cookbookNameTextview.setText(cookbook.getName());
        cookbookDescriptionTextview.setText(cookbook.getDescription());


    }
    

}