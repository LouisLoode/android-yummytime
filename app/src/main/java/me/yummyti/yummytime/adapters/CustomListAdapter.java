package me.yummyti.yummytime.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import me.yummyti.yummytime.ApplicationController;
import me.yummyti.yummytime.models.Users;

import me.yummyti.yummytime.R;


/**
 * Created by louisloode on 14/12/2016.
 */

public class CustomListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Users> usersItems;
    ImageLoader imageLoader = ApplicationController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<Users> usersItems) {
        this.activity = activity;
        this.usersItems = usersItems;
    }

    @Override
    public int getCount() {
        return usersItems.size();
    }

    @Override
    public Object getItem(int location) {
        return usersItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.users_list_row, null);

        if (imageLoader == null)
            imageLoader = ApplicationController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.image);
        TextView name = (TextView) convertView.findViewById(R.id.name);

        // getting billionaires data for the row
        Users m = usersItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getImage(), imageLoader);

        // name
        name.setText(m.getName());

        return convertView;
    }

}