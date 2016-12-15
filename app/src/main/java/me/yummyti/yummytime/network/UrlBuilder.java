package me.yummyti.yummytime.network;

/**
 * Created by louisloode on 13/12/2016.
 */

public class UrlBuilder {

    private static final String BASE_URL = "http://91.134.136.124:3001/";

    // Users URLS
    public static String getLoginUrl() {
        return BASE_URL+"login.json";
    }

    public static String getRegisterUrl() {
        return BASE_URL+"users.json";
    }

    public static String getUsersUrl() {
        return BASE_URL+"users.json";
    }

    public static String getUsersUrl(Integer id) {
        return BASE_URL+"users/"+id+".json";
    }


    // Recipies URLS
    public static String getRecipiesUrl() {
        return BASE_URL+"recipes.json";
    }

    public static String getRecipiesUrl(Integer id) {
        return BASE_URL+"recipes/"+id+".json";
    }

    public static String getRecipiesIngredientsUrl(Integer id) {
        return BASE_URL+"recipes/"+id+"/ingredients";
    }


    // Cookbooks URLS
    public static String getCookbooksUrl() {
        return BASE_URL+"cookbooks.json";
    }

    public static String getCookbooksUrl(Integer id) {
        return BASE_URL+"cookbooks/"+id+".json";
    }

    // Weeks URLS
    /*public static String getCookbooksUrl() {
        return BASE_URL+"cookbooks.json";
    }*/

    public static String getWeekUrl(Integer id) {
        return BASE_URL+"weeks/"+id+".json";
    }
}
