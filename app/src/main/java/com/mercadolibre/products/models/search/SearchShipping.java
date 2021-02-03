package com.mercadolibre.products.models.search;

import com.google.gson.annotations.SerializedName;

public class SearchShipping {

    @SerializedName("free_shipping")
    public String freeShipping;

    public SearchShipping(String freeShipping) {
        this.freeShipping = freeShipping;
    }

    public String getFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(String freeShipping) {
        this.freeShipping = freeShipping;
    }
}
