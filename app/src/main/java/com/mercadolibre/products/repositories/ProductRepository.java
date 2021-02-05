package com.mercadolibre.products.repositories;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mercadolibre.products.R;
import com.mercadolibre.products.models.details.Item;
import com.mercadolibre.products.models.details.ItemAttributes;
import com.mercadolibre.products.models.details.ItemPictures;
import com.mercadolibre.products.retrofit.ApiRequest;
import com.mercadolibre.products.retrofit.RetrofitRequest;
import com.mercadolibre.products.util.AppConstant;
import com.mercadolibre.products.util.Resource;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductRepository {

    private final String TAG = "ProductRepository";
    private final Context context;
    private final Retrofit retrofit;
    private final MutableLiveData<Resource<Item>> product;
    private final MutableLiveData<ArrayList<ItemAttributes>> attributes;
    private final MutableLiveData<ArrayList<ItemPictures>> pictures;

    public ProductRepository(Context context) {
        this.context = context;
        this.retrofit = RetrofitRequest.getRetrofitInstance();
        this.product = new MutableLiveData<>();
        this. attributes = new MutableLiveData<>();
        this. pictures = new MutableLiveData<>();
    }

    public void getProduct(String id){
        if(!AppConstant.isConnectionAvailable(context)){
            this.product.setValue(Resource.error(context.getString(R.string.conection),null));
            return;
        }
        product.postValue(Resource.loading(null));
        ApiRequest apiRequest = retrofit.create(ApiRequest.class);
        Call<Item> call = apiRequest.getProduct(id);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(@NonNull Call<Item> call, @NonNull Response<Item> response) {
                if(response.body()==null){
                    product.postValue(Resource.error("No se encontre el producto",null));
                }else {
                    response.body().setCondition(setTypeCondition(response.body().getCondition()));
                    response.body().setPrice(setPrice(response.body().getPrice()));
                    response.body().setSoldQuantity(response.body().getSoldQuantity().concat(" ").concat(setSoldQuatity(Integer.parseInt(response.body().getSoldQuantity()))));
                    attributes.postValue(setAttributes(response.body().getAttributes()));
                    product.postValue(Resource.success(response.body()));
                    pictures.postValue(response.body().getPictures());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Item> call,@NonNull Throwable t) {
                product.postValue(Resource.error("No se encontro el producto",null));
            }
        });
    }

    private String setTypeCondition(String condition){
        if(context.getString(R.string.product_conditions_new).equals(condition)){
            return context.getString(R.string.product_conditions_new_es);
        }else{
            return context.getString(R.string.product_conditions_used_es);
        }
    }

    private String setSoldQuatity(int sold){
        if(sold==1){
            return context.getString(R.string.product_sold);
        }else{
            return context.getString(R.string.product_sold_out);
        }
    }

    private String setPrice(String price){
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        return format.format(Float.parseFloat(price));
    }

    private ArrayList<ItemAttributes> setAttributes(ArrayList<ItemAttributes> attributes){

        for (int i = 0 ; i < attributes.size(); i++){
            if(attributes.get(i).getName()==null || attributes.get(i).getValue_name() == null || attributes.get(i).getValue_name().isEmpty() || attributes.get(i).getName().isEmpty()){
                attributes.remove(attributes.get(i));
            }
        }
    return attributes;
    }

    public LiveData<Resource<Item>> getProduct(){
        return this.product;
    }

    public LiveData<ArrayList<ItemAttributes>> getAttributes(){
        return this.attributes;
    }

    public LiveData<ArrayList<ItemPictures>> getPictures(){
        return this.pictures;
    }



}
