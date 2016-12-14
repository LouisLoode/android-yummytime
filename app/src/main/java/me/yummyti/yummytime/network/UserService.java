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
import me.yummyti.yummytime.models.User;

/**
 * Created by louisloode on 14/12/2016.
 */

public class UserService {

    private static final String USER_REQUEST_TAG = "users_request";

    public interface UsersListener {
        void onReceiveUsers(User[] users);
        void onFailed();
    }

    public UserService(){

    }

    public static void getUsers(final UsersListener listener) {

        String url = UrlBuilder.getUsersUrl();

        Log.d("YUMMTIME", "url : "+url);

        //Create the request
        JacksonRequest<User[]> request = createUsersRequest(url, listener);

        sendRequest(request);

    }

    public static void getUser(Integer id, UsersListener listener) {

        String url = UrlBuilder.getUsersUrl(id);

        //Create the request
        JacksonRequest<User[]> request = createUsersRequest(url, listener);

        // Before we send the request, first we cancel the previous request
        cancelRequest(USER_REQUEST_TAG);

        request.setTag(USER_REQUEST_TAG);
        sendRequest(request);
    }

    private static void cancelRequest(String beerRequestTag) {
        ApplicationController.getInstance()
                .getRequestQueue()
                .cancelAll(beerRequestTag);

    }

    private static JacksonRequest<User[]> createUsersRequest(String url,
                                                                 final UsersListener listener) {

        JacksonRequest<User[]> request =
                new JacksonRequest<User[]>(Request.Method.GET, url, new JacksonRequestListener<User[]>() {
                    @Override
                    public void onResponse(User[] response, int statusCode, VolleyError error) {

                        //response !!!

                        //Log.d("User_service", response.toString());

                        if(response!=null) {


                            if(listener!=null) {
                                listener.onReceiveUsers(response);
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
                        return ArrayType.construct(SimpleType.constructUnsafe(User.class),null,null);
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<String, String>();
                        //headers.put("Accept", "application/json");

                        //Integer userId = ((ApplicationController) getApplication()).getUserProfileToken();
                        //Integer userId = ApplicationController.getUserProfileToken();
                        //if (getMethod() == Method.POST || getMethod() == Method.PUT) {
                        //headers.put("Content-Type", "application/json");
                            headers.put("usersession", "7");
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