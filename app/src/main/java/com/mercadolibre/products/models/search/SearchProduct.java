package com.mercadolibre.products.models.search;

import com.google.gson.annotations.SerializedName;

public class SearchProduct {

    public String id;

    @SerializedName("title")
    public String title;

    public String price;

    public String condition;

    public String thumbnail;

    @SerializedName("searchShipping")
    public SearchShipping searchShipping;


}
