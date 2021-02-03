package com.mercadolibre.products.models.suggest;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Suggest {

    @SerializedName("suggested_queries")
    private ArrayList<SuggestQueries> suggestQueries;

    public Suggest(ArrayList<SuggestQueries> suggestQueries) {
        this.suggestQueries = suggestQueries;
    }

    public ArrayList<SuggestQueries> getSuggestQueries() {
        return suggestQueries;
    }

    public void setSuggestQueries(ArrayList<SuggestQueries> suggestQueries) {
        this.suggestQueries = suggestQueries;
    }
}