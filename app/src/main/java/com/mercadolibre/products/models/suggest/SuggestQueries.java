package com.mercadolibre.products.models.suggest;


import com.google.gson.annotations.SerializedName;

public class SuggestQueries {

    @SerializedName("q")
    String q;

    public SuggestQueries(String q) {
        this.q = q;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }
}