package com.mercadolibre.products.models.search;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Search {

    @SerializedName("paging")
    private SearchPagination paging;

    @SerializedName("results")
    private ArrayList<SearchProduct> productos;

    public Search() {
    }

    public Search(SearchPagination paging, ArrayList<SearchProduct> productos) {
        this.paging = paging;
        this.productos = productos;
    }

    public SearchPagination getPaging() {
        return paging;
    }

    public void setPaging(SearchPagination paging) {
        this.paging = paging;
    }

    public ArrayList<SearchProduct> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<SearchProduct> productos) {
        this.productos = productos;
    }
}
