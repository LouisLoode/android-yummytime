package me.yummyti.yummytime.network;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.spothero.volley.JacksonRequest;
import com.spothero.volley.JacksonRequestListener;

import java.util.HashMap;
import java.util.Map;

import me.yummyti.yummytime.ApplicationController;
import me.yummyti.yummytime.models.Recipe;

import static android.content.ContentValues.TAG;

/**
 * Created by louisloode on 14/12/2016.
 */

public class RecipeService {

    private static final String RECIPE_REQUEST_TAG = "users_request";

    public interface RecipiesListener {
        void onReceiveRecipes(Recipe[] recipes);
        void onFailed();
    }

    public RecipeService(){

    }

    public static void getRecipies(final RecipiesListener listener) {

        String url = UrlBuilder.getRecipiesUrl();

        Log.d("YUMMTIME", "url : "+url);

        //Create the request
        JacksonRequest<Recipe[]> request = createRecipesRequest(url, listener);

        sendRequest(request);

    }

    public static void getRecipe(Integer id, RecipiesListener listener) {

        String url = UrlBuilder.getRecipiesUrl(id);

        //Create the request
        JacksonRequest<Recipe[]> request = createRecipesRequest(url, listener);

        // Before we send the request, first we cancel the previous request
        cancelRequest(RECIPE_REQUEST_TAG);

        request.setTag(RECIPE_REQUEST_TAG);
        sendRequest(request);
    }

    private static void cancelRequest(String recipeRequestTag) {
        ApplicationController.getInstance()
                .getRequestQueue()
                .cancelAll(recipeRequestTag);

    }

    private static JacksonRequest<Recipe[]> createRecipesRequest(String url,
                                                                 final RecipiesListener listener) {

        JacksonRequest<Recipe[]> request =
                new JacksonRequest<Recipe[]>(Request.Method.GET, url, new JacksonRequestListener<Recipe[]>() {
                    @Override
                    public void onResponse(Recipe[] response, int statusCode, VolleyError error) {

                        //response !!!

                        //Log.d("User_service", response.toString());

                        if(response!=null) {


                            if(listener!=null) {
                                listener.onReceiveRecipes(response);
                            }
                        }

                        if(error!=null) {
                            if(listener!=null) {
                                listener.onFailed();
                            }
                        }

                    }

                    @Override
                    public JavaType getReturnType() {
                        // SimpleTyp=e
                        // ArrayType

                        //return SimpleType.construct(UserResult.class);
                        return ArrayType.construct(SimpleType.constructUnsafe(Recipe.class),null,null);
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<String, String>();
                        //headers.put("Accept", "application/json");


                        Integer userId = ApplicationController.getInstance().getUserProfileToken();
                        Log.e(TAG, "createGetUsersRequest:" + userId.toString());
                        //if (getMethod() == Method.POST || getMethod() == Method.PUT) {
                        //headers.put("Content-Type", "application/json");
                        //headers.put("usersession", "7");
                        headers.put("usersession", userId.toString());

                        return headers;
                    }
                };



        return request;
    }


    private static void sendRequest(JacksonRequest request) {
        ApplicationController.getInstance()
                .getRequestQueue()
                .add(request);

    }

}