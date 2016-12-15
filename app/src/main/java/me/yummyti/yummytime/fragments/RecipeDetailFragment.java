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
import me.yummyti.yummytime.models.Recipe;
import me.yummyti.yummytime.models.RecipeLabels;

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link RecipeDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeDetailFragment extends Fragment {

    @BindView(R.id.detail_recipe_imageview)
    protected NetworkImageView networkImageView;

    @BindView(R.id.detail_recipe_name_textview)
    protected TextView recipeNameTextview;

    @BindView(R.id.detail_recipe_description_textview)
    protected TextView recipeDescriptionTextview;

    private Recipe recipe;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    public static RecipeDetailFragment newInstance(Recipe recipe) {

        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();

        // Not good at all
        recipeDetailFragment.recipe = recipe;


        return recipeDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        ImageLoader imageLoader = ApplicationController.getInstance()
                .getImageLoader();

        RecipeLabels recipeLabels = recipe.getRecipeLabels();

        if(recipeLabels!=null) {
            String urlImageLarge = recipeLabels.getLarge();

            networkImageView.setImageUrl(urlImageLarge, imageLoader);
        }

        recipeNameTextview.setText(recipe.getName());
        recipeDescriptionTextview.setText(recipe.getDescription());


    }
    

}