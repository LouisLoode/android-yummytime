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
import me.yummyti.yummytime.models.Cookbook;

/**
 * Created by louisloode on 14/12/2016.
 */

public class CookbookService {

    private static final String COOKBOOK_REQUEST_TAG = "cookbooks_request";

    public interface CookbooksListener {
        void onReceiveCookbooks(Cookbook[] cookbooks);
        void onFailed();
    }

    public CookbookService(){

    }

    public static void getCookbooks(final CookbooksListener listener) {

        String url = UrlBuilder.getCookbooksUrl();

        Log.d("YUMMTIME", "url : "+url);

        //Create the request
        JacksonRequest<Cookbook[]> request = createCookbooksRequest(url, listener);

        sendRequest(request);

    }

    public static void getCookbook(Integer id, CookbooksListener listener) {

        String url = UrlBuilder.getCookbooksUrl(id);

        //Create the request
        JacksonRequest<Cookbook[]> request = createCookbooksRequest(url, listener);

        // Before we send the request, first we cancel the previous request
        cancelRequest(COOKBOOK_REQUEST_TAG);

        request.setTag(COOKBOOK_REQUEST_TAG);
        sendRequest(request);
    }

    private static void cancelRequest(String cookbookRequestTag) {
        ApplicationController.getInstance()
                .getRequestQueue()
                .cancelAll(cookbookRequestTag);

    }

    private static JacksonRequest<Cookbook[]> createCookbooksRequest(String url,
                                                                 final CookbooksListener listener) {

        JacksonRequest<Cookbook[]> request =
                new JacksonRequest<Cookbook[]>(Request.Method.GET, url, new JacksonRequestListener<Cookbook[]>() {
                    @Override
                    public void onResponse(Cookbook[] response, int statusCode, VolleyError error) {

                        //response !!!

                        //Log.d("User_service", response.toString());

                        if(response!=null) {


                            if(listener!=null) {
                                listener.onReceiveCookbooks(response);
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
                        return ArrayType.construct(SimpleType.constructUnsafe(Cookbook.class),null,null);
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<String, String>();
                        //headers.put("Accept", "application/json");


                        Integer userId = ApplicationController.getInstance().getUserProfileToken();
                        //Log.e(TAG, "createGetUsersRequest:" + userId.toString());
                        headers.put("usersession", userId.toString());

                        //Integer userId = ((ApplicationController) getApplication()).getUserProfileToken();
                        //Integer userId = ApplicationController.getUserProfileToken();
                        //if (getMethod() == Method.POST || getMethod() == Method.PUT) {
                        //headers.put("Content-Type", "application/json");
                        //}

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