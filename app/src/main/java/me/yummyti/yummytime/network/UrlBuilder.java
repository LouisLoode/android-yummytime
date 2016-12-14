package me.yummyti.yummytime.network;

/**
 * Created by louisloode on 13/12/2016.
 */

public class UrlBuilder {

    private static final String BASE_URL = "http://91.134.136.124:3001/";


    public static String getUsersUrl() {
        return BASE_URL+"users.json";
    }

    public static String getUsersUrl(Integer id) {
        return BASE_URL+"users/"+id+".json";
    }
}
