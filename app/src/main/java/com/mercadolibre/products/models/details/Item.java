package com.mercadolibre.products.models.details;

import android.content.Context;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.mercadolibre.products.R;

import java.util.ArrayList;

public class Item {

    String id;
    String title;
    String price;
    @SerializedName("available_quantity")
    int availableQuantity;
    @SerializedName("sold_quantity")
    String soldQuantity;
    String condition;
    @SerializedName("pictures")
    ArrayList<ItemPictures> pictures;
    @SerializedName("attributes")
    ArrayList<ItemAttributes> attributes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(String soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public ArrayList<ItemPictures> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<ItemPictures> pictures) {
        this.pictures = pictures;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public ArrayList<ItemAttributes> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<ItemAttributes> attributes) {
        this.attributes = attributes;
    }

    public ArrayList<String> getPictureURL(){
        if(pictures==null) return new ArrayList<>();
        ArrayList<String> url = new ArrayList<>();
        for (int i = 0 ; i < pictures.size(); i++){
            Log.e("LogApp","Url: "+pictures.get(i).getUrl());
            url.add(pictures.get(i).url);
        }
        return url;
    }
}
