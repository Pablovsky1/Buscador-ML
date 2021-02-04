package com.mercadolibre.products.models.details;


import com.google.gson.annotations.SerializedName;

public class ItemPictures {
    @SerializedName("secure_url")
    String url;

    public ItemPictures(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
