package me.yummyti.yummytime.adapters;


import android.content.Context;
import android.util.Log;
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
import me.yummyti.yummytime.models.Recipe;


public class RecipeAdapter extends BaseAdapter {

    protected class RecipeViewHolder {


        @BindView(R.id.recipe_image)
        protected NetworkImageView networkImageView;

        @BindView(R.id.recipe_name)
        protected TextView textViewNameRecipe;

        public RecipeViewHolder(View recipeRowView) {
            ButterKnife.bind(this, recipeRowView);
        }

        public void setRecipe(Recipe recipe) {
            Log.d("NAME_RECIPE",recipe.getName());
            textViewNameRecipe.setText(recipe.getName());
        }
    }

    private final LayoutInflater layoutInflater;
    private List<Recipe> recipes;   //null

    public RecipeAdapter(Context context) {
        recipes = new ArrayList<>();
        layoutInflater = LayoutInflater.from(context);
    }

    public void refresh(Recipe[] recipes) {
        if(recipes==null) {
            refresh(new ArrayList<Recipe>());
        } else {
            refresh(Arrays.asList(recipes));
        }
    }

    public void refresh(List<Recipe> recipeList) {
        this.recipes.clear();
        this.recipes.addAll(recipeList);

        //Refresh UI
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public Object getItem(int i) {
        return recipes.get(i);
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
        RecipeViewHolder recipeViewHolder;

        if(rowView==null) {
            rowView = layoutInflater.inflate(R.layout.recipies_list_row, viewGroup, false);
            recipeViewHolder = new RecipeViewHolder(rowView);

            rowView.setTag(recipeViewHolder);
        } else {
            recipeViewHolder = (RecipeViewHolder)rowView.getTag();
        }

        Recipe recipe = recipes.get(position);

        recipeViewHolder.setRecipe(recipe);

        return rowView;
    }
}
