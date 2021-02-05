package com.mercadolibre.products.repositories;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mercadolibre.products.models.suggest.Suggest;
import com.mercadolibre.products.models.suggest.SuggestQueries;
import com.mercadolibre.products.retrofit.ApiRequest;
import com.mercadolibre.products.retrofit.RetrofitRequest;
import com.mercadolibre.products.util.AppConstant;
import com.mercadolibre.products.util.MyLog;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SuggestRepository {
    private final String TAG = "SuggestRepository";
    private final Context context;
    private final Retrofit retrofit;
    private final MutableLiveData<ArrayList<SuggestQueries>> suggest;

    public SuggestRepository(Context context) {
        this.retrofit = RetrofitRequest.getRetrofitInstance();
        this.suggest = new MutableLiveData<>();
        this.context = context;
    }

    public void getSuggest(String item){
        if(!AppConstant.isConnectionAvailable(context)){
            return;
        }

        if(item==null || item.isEmpty()){
            suggest.postValue(new ArrayList<>());
            return;
        }
        ApiRequest apiRequest = retrofit.create(ApiRequest.class);
        Call<Suggest> call = apiRequest.getSuggest(item, 6,true);
        call.enqueue(new Callback<Suggest>() {
            @Override
            public void onResponse(@NonNull Call<Suggest> call, @NonNull Response<Suggest> response) {
                if(response.body()==null || response.body().getSuggestQueries().size()<=0){
                    MyLog.e(TAG,"Search null");
                }else {
                    suggest.postValue(response.body().getSuggestQueries());
                    MyLog.i(TAG, "Search success suggest: " + response.body().getSuggestQueries().size());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Suggest> call,@NonNull Throwable t) {
                MyLog.e(TAG,"Search error: "+t.getMessage());
            }
        });
    }

    public LiveData<ArrayList<SuggestQueries>> getSuggest(){
        return this.suggest;
    }

}
