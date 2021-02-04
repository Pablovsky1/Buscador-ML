package com.mercadolibre.products.models.details;


import androidx.annotation.NonNull;

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

    @NonNull
    @Override
    public String toString() {
        return "ItemPictures{" +
                "url='" + url + '\'' +
                '}';
    }
}
