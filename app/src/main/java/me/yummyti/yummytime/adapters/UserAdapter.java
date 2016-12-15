package me.yummyti.yummytime.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yummyti.yummytime.R;
import me.yummyti.yummytime.models.User;


public class UserAdapter extends BaseAdapter {

    protected class UserViewHolder {

        @BindView(R.id.user_image)
        protected NetworkImageView networkImageView;

        @BindView(R.id.user_name)
        protected TextView textViewNameUser;

        public UserViewHolder(View userRowView) {
            ButterKnife.bind(this, userRowView);
        }

        public void setUser(User user) {

            textViewNameUser.setText(user.getName());
        }
    }

    private final LayoutInflater layoutInflater;
    private List<User> users;   //null

    public UserAdapter(Context context) {
        users = new ArrayList<>();
        layoutInflater = LayoutInflater.from(context);
    }

    public void refresh(User[] users) {
        if(users==null) {
            refresh(new ArrayList<User>());
        } else {
            refresh(Arrays.asList(users));
        }
    }

    public void refresh(List<User> userList) {
        this.users.clear();
        this.users.addAll(userList);

        //Refresh UI
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View viewFromCache, ViewGroup viewGroup) {

        View rowView = viewFromCache;

        //Utilisation d'un ViewHolder
        // https://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder
        UserViewHolder userViewHolder;

        if(rowView==null) {
            rowView = layoutInflater.inflate(R.layout.users_list_row, viewGroup, false);
            userViewHolder = new UserViewHolder(rowView);

            rowView.setTag(userViewHolder);
        } else {
            userViewHolder = (UserViewHolder)rowView.getTag();
        }

        User user = users.get(position);

        userViewHolder.setUser(user);

        return rowView;
    }
}
