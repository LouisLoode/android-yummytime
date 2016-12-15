package me.yummyti.yummytime.network;

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
import me.yummyti.yummytime.models.Week;

/**
 * Created by louisloode on 14/12/2016.
 */

public class WeekService {

    private static final String WEEK_REQUEST_TAG = "weeks_request";

    public interface WeeksListener {
        void onReceiveWeeks(Week weeks);
        void onFailed();
    }

    public WeekService(){

    }

    public static void getWeek(Integer id, WeeksListener listener) {

        String url = UrlBuilder.getWeekUrl(id);

        //Create the request
        JacksonRequest<Week> request = createGetWeekRequest(url, listener);

        // Before we send the request, first we cancel the previous request
        cancelRequest(WEEK_REQUEST_TAG);

        request.setTag(WEEK_REQUEST_TAG);
        sendRequest(request);
    }

    private static void cancelRequest(String weekRequestTag) {
        ApplicationController.getInstance()
                .getRequestQueue()
                .cancelAll(weekRequestTag);

    }

    private static JacksonRequest<Week> createGetWeekRequest(String url,
                                                                 final WeeksListener listener) {

        JacksonRequest<Week> request =
                new JacksonRequest<Week>(Request.Method.GET, url, new JacksonRequestListener<Week>() {
                    @Override
                    public void onResponse(Week response, int statusCode, VolleyError error) {

                        //response !!!

                        //Log.d("User_service", response.toString());

                        if(response!=null) {


                            if(listener!=null) {
                                listener.onReceiveWeeks(response);
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
                        return ArrayType.construct(SimpleType.constructUnsafe(Week.class),null,null);
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<String, String>();
                        //headers.put("Accept", "application/json");


                        //Integer userId = ApplicationController.getInstance().getUserProfileToken();
                        //headers.put("usersession", userId.toString());
                        //Log.e("USERID", userId.toString());

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