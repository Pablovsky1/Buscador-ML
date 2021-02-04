package com.mercadolibre.products.models.search;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Search {

    @SerializedName("paging")
    private SearchPagination paging;

    @SerializedName("results")
    private ArrayList<SearchProduct> productos;



    public Search(SearchPagination paging, ArrayList<SearchProduct> productos) {
        this.paging = paging;
        this.productos = productos;
    }

    public SearchPagination getPaging() {
        return paging;
    }


    public ArrayList<SearchProduct> getProductos() {
        return productos;
    }

    @NonNull
    @Override
    public String toString() {
        return "Search{" +
                "paging=" + paging +
                ", productos=" + productos +
                '}';
    }
}
