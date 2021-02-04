package com.mercadolibre.products.models.details;

import androidx.annotation.NonNull;

public class ItemAttributes {

    private String name;
    private String value_name;


    public String getName() {
        return name;
    }


    public String getValue_name() {
        return value_name;
    }


    @NonNull
    @Override
    public String toString() {
        return "ItemAttributes{" +
                "name='" + name + '\'' +
                ", value_name='" + value_name + '\'' +
                '}';
    }
}
