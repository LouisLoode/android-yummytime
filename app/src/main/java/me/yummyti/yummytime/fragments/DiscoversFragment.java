package me.yummyti.yummytime.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.yummyti.yummytime.ApplicationController;
import me.yummyti.yummytime.R;
import me.yummyti.yummytime.adapters.CustomListAdapter;
import me.yummyti.yummytime.models.Users;


public class DiscoversFragment extends android.support.v4.app.Fragment {
    public static final String ARGS_INSTANCE = "me.yummyti.yummytime.argsInstance";
    // Log tag
    private static final String TAG = DiscoversFragment.class.getSimpleName();

    // Billionaires json url
    private static final String url = "https://raw.githubusercontent.com/mobilesiri/Android-Custom-Listview-Using-Volley/master/richman.json";
    private ProgressDialog pDialog;
    private List<Users> usersList = new ArrayList<Users>();
    private ListView listView;
    private CustomListAdapter mFeedAdapter;

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
        return view;
    }


    public void onViewCreated() {

        // pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // Creating volley request obj
        JsonArrayRequest chiefReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++)
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Users users = new Users();
                                Users.setId(obj.getInt("id"));
                                Users.setName(obj.getString("name"));
                                Users.setImage(obj.getString("image"));


                                // adding Billionaire to worldsBillionaires array
                                usersList.add(users);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        mFeedAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        });

        // Adding request to request queue
        ApplicationController.getInstance().addToRequestQueue(chiefReq);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseFragment.FragmentNavigation) {
            mFragmentNavigation = (BaseFragment.FragmentNavigation) context;
        }
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
