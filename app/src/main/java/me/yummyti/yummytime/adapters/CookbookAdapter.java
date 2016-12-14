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
import me.yummyti.yummytime.models.Cookbook;


public class CookbookAdapter extends BaseAdapter {

    protected class CookbookViewHolder {

        @BindView(R.id.cookbook_image)
        protected NetworkImageView networkImageView;

        @BindView(R.id.cookbook_name)
        protected TextView textViewNameCookbook;

        public CookbookViewHolder(View cookbookRowView) {
            ButterKnife.bind(this, cookbookRowView);
        }

        public void setCookbook(Cookbook cookbook) {

            textViewNameCookbook.setText(cookbook.getName());
        }
    }

    private final LayoutInflater layoutInflater;
    private List<Cookbook> cookbooks;   //null

    public CookbookAdapter(Context context) {
        cookbooks = new ArrayList<>();
        layoutInflater = LayoutInflater.from(context);
    }

    public void refresh(Cookbook[] cookbooks) {
        if(cookbooks==null) {
            refresh(new ArrayList<Cookbook>());
        } else {
            refresh(Arrays.asList(cookbooks));
        }
    }

    public void refresh(List<Cookbook> cookbookList) {
        this.cookbooks.clear();
        this.cookbooks.addAll(cookbookList);

        //Refresh UI
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return cookbooks.size();
    }

    @Override
    public Object getItem(int i) {
        return cookbooks.get(i);
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
        CookbookViewHolder cookbookViewHolder;

        if(rowView==null) {
            rowView = layoutInflater.inflate(R.layout.cookbooks_list_row, viewGroup, false);
            cookbookViewHolder = new CookbookViewHolder(rowView);

            rowView.setTag(cookbookViewHolder);
        } else {
            cookbookViewHolder = (CookbookViewHolder)rowView.getTag();
        }

        Cookbook cookbook = cookbooks.get(position);

        cookbookViewHolder.setCookbook(cookbook);

        return rowView;
    }
}
