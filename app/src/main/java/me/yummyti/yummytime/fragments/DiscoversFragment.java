package me.yummyti.yummytime.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import me.yummyti.yummytime.R;
import me.yummyti.yummytime.adapters.CookbookAdapter;
import me.yummyti.yummytime.adapters.RecipeAdapter;
import me.yummyti.yummytime.adapters.UserAdapter;
import me.yummyti.yummytime.models.Cookbook;
import me.yummyti.yummytime.models.Recipe;
import me.yummyti.yummytime.models.User;
import me.yummyti.yummytime.network.CookbookService;
import me.yummyti.yummytime.network.RecipeService;
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

   // @BindView(R.id.usersListView)
   // protected ListView usersListView;

    @BindView(R.id.cookbooksListView)
    protected ListView cookbooksListView;

    //@BindView(R.id.recipesListView)
    //protected ListView recipiesListView;

   // private UserAdapter userAdapter;
    private CookbookAdapter cookbookAdapter;
   // private RecipeAdapter recipeAdapter;

    BaseFragment.FragmentNavigation mFragmentNavigation;

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

       // userAdapter = new UserAdapter(getContext());
        cookbookAdapter = new CookbookAdapter(getContext());
      //  recipeAdapter = new RecipeAdapter(getContext());

       // usersListView.setAdapter(userAdapter);
        cookbooksListView.setAdapter(cookbookAdapter);
       // recipiesListView.setAdapter(recipeAdapter);

        showWaitingView();

       /* UserService.getUsers(new UserService.UsersListener() {
            @Override
            public void onReceiveUsers(User[] users) {
                userAdapter.refresh(users);
                hideWaitingView();
            }


            @Override
            public void onFailed() {
                hideWaitingView();
            }
        });*/


        CookbookService.getCookbooks(new CookbookService.CookbooksListener() {
            @Override
            public void onReceiveCookbooks(Cookbook[] cookbooks) {
                cookbookAdapter.refresh(cookbooks);
                hideWaitingView();
            }


            @Override
            public void onFailed() {
                hideWaitingView();
            }
        });

        /* RecipeService.getRecipies(new RecipeService.RecipiesListener() {
            @Override
            public void onReceiveRecipes(Recipe[] recipes) {
                recipeAdapter.refresh(recipes);
                hideWaitingView();
            }


            @Override
            public void onFailed() {
                hideWaitingView();
            }
        });*/

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

   /* @OnItemClick(R.id.recipesListView)
    public void onClickRecipe(AdapterView<?> adapterView, View view, int position, long id) {

        Recipe recipe = (Recipe) recipeAdapter.getItem(position);

        RecipeDetailFragment recipeDetailFragment = RecipeDetailFragment.newInstance(recipe);

        // R.id.activity_home = id container de cette activité

        getFragmentManager()
                .beginTransaction()
                .add(R.id.activity_home, recipeDetailFragment)
                .addToBackStack(null)
                .commit();

    }*/


    @OnItemClick(R.id.cookbooksListView)
    public void onClickCookbook(AdapterView<?> adapterView, View view, int position, long id) {

        Cookbook cookbook = (Cookbook) cookbookAdapter.getItem(position);

        CookbookDetailFragment cookbookDetailFragment = CookbookDetailFragment.newInstance(cookbook);

        // R.id.activity_home = id container de cette activité

        getFragmentManager()
                .beginTransaction()
                .add(R.id.activity_home, cookbookDetailFragment)
                .addToBackStack(null)
                .commit();

    }

   /* @OnItemClick(R.id.usersListView)
    public void onClickUser(AdapterView<?> adapterView, View view, int position, long id) {

        User user = (User) userAdapter.getItem(position);

        UserDetailFragment userDetailFragment = UserDetailFragment.newInstance(user);

        // R.id.activity_home = id container de cette activité

        getFragmentManager()
                .beginTransaction()
                .add(R.id.activity_home, userDetailFragment)
                .addToBackStack(null)
                .commit();

    } */

    private void showWaitingView() {
        waitingView.setVisibility(View.VISIBLE);
    }

    private void hideWaitingView() {
        waitingView.setVisibility(View.INVISIBLE);
    }
}
