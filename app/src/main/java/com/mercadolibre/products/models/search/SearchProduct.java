package com.mercadolibre.products.models.search;

import com.google.gson.annotations.SerializedName;

public class SearchProduct {

    private String id;

    @SerializedName("title")
    private String title;

    private String price;

    private String condition;

    private String thumbnail;

    @SerializedName("searchShipping")
    private SearchShipping searchShipping;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public SearchShipping getSearchShipping() {
        return searchShipping;
    }

    public void setSearchShipping(SearchShipping searchShipping) {
        this.searchShipping = searchShipping;
    }

    @Override
    public String toString() {
        return "SearchProduct{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", condition='" + condition + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", searchShipping=" + searchShipping +
                '}';
    }
}
