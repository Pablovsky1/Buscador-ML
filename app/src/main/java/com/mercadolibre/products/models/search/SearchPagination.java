package com.mercadolibre.products.models.search;

import com.google.gson.annotations.SerializedName;

public class SearchPagination {

    @SerializedName("total")
    private Integer total;

    @SerializedName("primary_results")
    private Integer primary_results;

    @SerializedName("offset")
    private Integer offset;

    @SerializedName("limit")
    private Integer limit;

    public SearchPagination(Integer total, Integer primary_results, Integer offset, Integer limit) {
        this.total = total;
        this.primary_results = primary_results;
        this.offset = offset;
        this.limit = limit;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPrimary_results() {
        return primary_results;
    }

    public void setPrimary_results(Integer primary_results) {
        this.primary_results = primary_results;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "SearchPagination{" +
                "total=" + total +
                ", primary_results=" + primary_results +
                ", offset=" + offset +
                ", limit=" + limit +
                '}';
    }
}
