package me.yummyti.yummytime.network;

/**
 * Created by louisloode on 13/12/2016.
 */

public class UrlBuilder {

    private static final String BASE_URL = "http://api.brewerydb.com/";


    public static String getBeersUrl() {
        return BASE_URL+"v2/beers?abv=-12";
    }

    public static String getBeersUrl(String name) {
        return BASE_URL+"v2/beers?name="+name;
    }
}
