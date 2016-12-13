package me.yummyti.yummytime;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import me.yummyti.yummytime.controllers.FragNavController;
import me.yummyti.yummytime.fragments.BaseFragment;
import me.yummyti.yummytime.fragments.CartsFragment;
import me.yummyti.yummytime.fragments.DiscoversFragment;
import me.yummyti.yummytime.fragments.WeeksFragment;


public class MainActivity extends AppCompatActivity implements BaseFragment.FragmentNavigation, FragNavController.TransactionListener, FragNavController.RootFragmentListener {
    private BottomBar mBottomBar;
    private FragNavController mNavController;

    //Better convention to properly name the indices what they are in your app
    private final int INDEX_DISCOVERS = FragNavController.TAB1;
    private final int INDEX_CARTS = FragNavController.TAB2;
    private final int INDEX_WEEKS = FragNavController.TAB3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tabs);

        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mBottomBar.selectTabAtPosition(INDEX_DISCOVERS);

        mNavController =
                new FragNavController(savedInstanceState, getSupportFragmentManager(), R.id.container,this,5, INDEX_DISCOVERS);
        mNavController.setTransactionListener(this);

        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.bb_menu_discovers:
                        mNavController.switchTab(INDEX_DISCOVERS);
                        break;
                    case R.id.bb_menu_carts:
                        mNavController.switchTab(INDEX_CARTS);
                        break;
                    case R.id.bb_menu_weeks:
                        mNavController.switchTab(INDEX_WEEKS);
                        break;
                }
            }
        });

        mBottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId){
                mNavController.clearStack();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (mNavController.canPop()) {
            mNavController.pop();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mNavController != null) {
            mNavController.onSaveInstanceState(outState);
        }
    }

    @Override
    public void pushFragment(Fragment fragment) {
        if (mNavController != null) {
            mNavController.push(fragment);
        }
    }

    @Override
    public void onTabTransaction(Fragment fragment, int index) {
        // If we have a backstack, show the back button
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(mNavController.canPop());
        }
    }

    @Override
    public void onFragmentTransaction(Fragment fragment) {
        //do fragmentty stuff. Maybe change title, I'm not going to tell you how to live your life
        // If we have a backstack, show the back button
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(mNavController.canPop());
        }
    }

    @Override
    public Fragment getRootFragment(int index) {
        switch (index) {
            case INDEX_DISCOVERS:
                return DiscoversFragment.newInstance(0);
            case INDEX_CARTS:
                return CartsFragment.newInstance(0);
            case INDEX_WEEKS:
                return WeeksFragment.newInstance(0);
        }
        throw new IllegalStateException("Need to send an index that we know");
    }

}