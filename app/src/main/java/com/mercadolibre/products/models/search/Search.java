package com.mercadolibre.products.models.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Search {

    @SerializedName("paging")
    private SearchPagination paging;

    @SerializedName("results")
    private List<SearchProduct> productos;

    public Search() {
    }

    public Search(SearchPagination paging, List<SearchProduct> productos) {
        this.paging = paging;
        this.productos = productos;
    }

    public SearchPagination getPaging() {
        return paging;
    }

    public void setPaging(SearchPagination paging) {
        this.paging = paging;
    }

    public List<SearchProduct> getProductos() {
        return productos;
    }

    public void setProductos(List<SearchProduct> productos) {
        this.productos = productos;
    }
}
