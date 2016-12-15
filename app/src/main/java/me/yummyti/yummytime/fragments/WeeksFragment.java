package me.yummyti.yummytime.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yummyti.yummytime.R;
import me.yummyti.yummytime.adapters.RecipeAdapter;
import me.yummyti.yummytime.models.Recipe;
import me.yummyti.yummytime.network.RecipeService;


public class WeeksFragment extends Fragment {
    public static final String ARGS_INSTANCE = "me.yummyti.yummytime.argsInstance";

    @BindView(R.id.waitingView)
    RelativeLayout waitingView;

    @BindView(R.id.recipesListView)
    protected ListView recipiesListView;

    private RecipeAdapter recipeAdapter;

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

        ButterKnife.bind(this, view);

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
        return view;
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
