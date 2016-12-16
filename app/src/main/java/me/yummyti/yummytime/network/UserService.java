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

import static android.content.ContentValues.TAG;

/**
 * Created by louisloode on 14/12/2016.
 */

public class UserService {

    private static final String USER_REQUEST_TAG = "users_request";
    private static final String LOGIN_REQUEST_TAG = "login_request";

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
        JacksonRequest<User[]> request = createGetUsersRequest(url, listener);

        sendRequest(request);

    }

    public static void getUser(Integer id, UsersListener listener) {

        String url = UrlBuilder.getUsersUrl(id);

        //Create the request
        JacksonRequest<User[]> request = createGetUsersRequest(url, listener);

        // Before we send the request, first we cancel the previous request
        cancelRequest(USER_REQUEST_TAG);

        request.setTag(USER_REQUEST_TAG);
        sendRequest(request);
    }

    public interface UserPostResultListener {
        void onPostUsers(User users);
        void onFailed();
    }


    public static void loginUser(String email, String password, UserPostResultListener listener) {

        String url = UrlBuilder.getLoginUrl();

        Map<String, String> params = new HashMap<String, String>();
        params.put("mail", email);
        params.put("password", password);

        //Create the request
        JacksonRequest<User> request = createPostUsersRequest(url, params, listener);

        // Before we send the request, first we cancel the previous request

        sendRequest(request);
    }



    public static void registerUser(String name, String email, String password, String password_confirmation, UserPostResultListener listener) {

        String url = UrlBuilder.getRegisterUrl();

        Map<String, String> params = new HashMap<String, String>();
        Log.e("DEBUG_NAME:", name);
        Log.e("DEBUG_MAIL:", email);
        Log.e("DEBUG_PASS1:", password);
        Log.e("DEBUG_PASS2:", password_confirmation);
        params.put("name", name);
        params.put("mail", email);
        params.put("password", password);
        params.put("password_confirmation", password_confirmation);


        //Create the request
        JacksonRequest<User> request = createPostUsersRequest(url, params, listener);

        // Before we send the request, first we cancel the previous request
        sendRequest(request);
    }

    private static void cancelRequest(String userRequestTag) {
        ApplicationController.getInstance()
                .getRequestQueue()
                .cancelAll(userRequestTag);

    }

    private static JacksonRequest<User[]> createGetUsersRequest(String url,
                                                                final UsersListener listener) {

        JacksonRequest<User[]> request =
                new JacksonRequest<User[]>(Request.Method.GET, url, new JacksonRequestListener<User[]>() {
                    @Override
                    public void onResponse(User[] response, int statusCode, VolleyError error) {

                        //response !!!

                        Log.d("User_service", response.toString());

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


                        //Integer userId = ApplicationController.getInstance().getUserProfileToken();
                        //headers.put("usersession", userId.toString());
                        //Log.e("USERID", userId.toString());

                        Integer userId = ApplicationController.getInstance().getUserProfileToken();
                        Log.e(TAG, "createGetUsersRequest:" + userId.toString());
                        //if (getMethod() == Method.POST || getMethod() == Method.PUT) {
                        //headers.put("Content-Type", "application/json");
                        //headers.put("usersession", "7");
                        headers.put("usersession", userId.toString());
                        //}

                        return headers;
                    }
                };



        return request;
    }


    private static JacksonRequest<User> createPostUsersRequest(String url, Map params,
                                                                final UserPostResultListener listener) {

        JacksonRequest<User> request =
                new JacksonRequest<User>(Request.Method.POST, url, params, new JacksonRequestListener<User>() {
                    @Override
                    public void onResponse(User response, int statusCode, VolleyError error) {

                        //response !!!
                        //Log.e("user_POST_service", String.valueOf(statusCode));

                        if(error != null){
                            if(error!=null) {
                                if(listener!=null) {
                                    listener.onFailed();
                                }
                            }
                        }else {
                            if(response!=null) {

                                if(listener!=null) {
                                    listener.onPostUsers(response);
                                }
                            }
                        }
                    }

                    @Override
                    public JavaType getReturnType() {
                        // SimpleTyp=e
                        // ArrayType

                        return SimpleType.construct(User.class);
                        //return ArrayType.construct(SimpleType.constructUnsafe(User.class),null,null);
                    }
                }) {
                    @Override
                    public String getBodyContentType() {
                        return "application/json";
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Accept", "application/json");

                        if (getMethod() == Method.POST || getMethod() == Method.PUT) {
                            headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf8");
                        }

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