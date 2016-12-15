package me.yummyti.yummytime.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import me.yummyti.yummytime.ApplicationController;
import me.yummyti.yummytime.R;
import me.yummyti.yummytime.adapters.RecipeAdapter;
import me.yummyti.yummytime.models.Cookbook;
import me.yummyti.yummytime.models.CookbookLabels;
import me.yummyti.yummytime.models.Recipe;
import me.yummyti.yummytime.network.RecipeService;

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

    @BindView(R.id.detail_cookbook_chef_textview)
    protected TextView cookbookChefTextview;

    @BindView(R.id.detail_cookbook_description_textview)
    protected TextView cookbookDescriptionTextview;

    @BindView(R.id.recipesListView)
    protected ListView recipiesListView;

    @BindView(R.id.waitingView)
    RelativeLayout waitingView;

    private RecipeAdapter recipeAdapter;

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


        recipeAdapter = new RecipeAdapter(getContext());


        recipiesListView.setAdapter(recipeAdapter);

        showWaitingView();



        RecipeService.getRecipies(new RecipeService.RecipiesListener() {

            @Override
            public void onReceiveRecipes(Recipe[] recipes) {
                recipeAdapter.refresh(recipes);
                hideWaitingView();
            }


            @Override
            public void onFailed() {
                hideWaitingView();
            }
        });

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
        cookbookChefTextview.setText(cookbook.getChef());
        cookbookDescriptionTextview.setText(cookbook.getDescription());


    }

    @OnItemClick(R.id.recipesListView)
    public void onClickRecipe(AdapterView<?> adapterView, View view, int position, long id) {

        Recipe recipe = (Recipe) recipeAdapter.getItem(position);

        RecipeDetailFragment recipeDetailFragment = RecipeDetailFragment.newInstance(recipe);

        // R.id.activity_home = id container de cette activité

        getFragmentManager()
                .beginTransaction()
                .add(R.id.activity_home, recipeDetailFragment)
                .addToBackStack(null)
                .commit();


    }


    private void showWaitingView() {
        waitingView.setVisibility(View.VISIBLE);
    }

    private void hideWaitingView() {
        waitingView.setVisibility(View.INVISIBLE);
    }
    

}