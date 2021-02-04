package com.mercadolibre.products.models.details;


import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Item {

    private String id;
    private String title;
    private String price;
    @SerializedName("available_quantity")
    private int availableQuantity;
    @SerializedName("sold_quantity")
    private String soldQuantity;
    private String condition;
    @SerializedName("pictures")
    private ArrayList<ItemPictures> pictures;
    @SerializedName("attributes")
    private ArrayList<ItemAttributes> attributes;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
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

    public int getAvailableQuantity() {
        return availableQuantity;
    }


    public ArrayList<ItemAttributes> getAttributes() {
        return attributes;
    }

    @NonNull
    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", availableQuantity=" + availableQuantity +
                ", soldQuantity='" + soldQuantity + '\'' +
                ", condition='" + condition + '\'' +
                ", pictures=" + pictures +
                ", attributes=" + attributes +
                '}';
    }
}
