package me.yummyti.yummytime.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by louisloode on 14/12/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeResult {
    private int currentPage;
    private int numberOfPages;
    private int totalResults;

    private Recipe[] data;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public Recipe[] getData() {
        return data;
    }

    public void setData(Recipe[] data) {
        this.data = data;
    }
}