package com.mercadolibre.products.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mercadolibre.products.R;
import com.mercadolibre.products.models.search.Search;
import com.mercadolibre.products.retrofit.ApiRequest;
import com.mercadolibre.products.retrofit.RetrofitRequest;
import com.mercadolibre.products.util.MyLog;
import com.mercadolibre.products.util.Resource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchRepository {
    private final String LOG = "SearchRepository";
    private final Application application;
    private Retrofit retrofit;
    private MutableLiveData<Resource<Search>> search;

    public SearchRepository(Application application) {
        this.application = application;
        this.retrofit = RetrofitRequest.getRetrofitInstance();
        this.search = new MutableLiveData<>();
    }

    public void getSearch(String item, int offset, int limit){
        this.search.setValue(Resource.loading(null));
        ApiRequest apiRequest = retrofit.create(ApiRequest.class);
        Call<Search> call = apiRequest.getItems(item, offset, limit);
        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                if(response.body()==null || response.body().getProductos().size()<=0){
                    search.postValue(Resource.error(application.getString(R.string.search_not_found)+"\n"+application.getString(R.string.search_not_found_advice),null));
                    MyLog.e(LOG,"Search null");
                }else {
                    search.postValue(Resource.success(response.body()));
                    MyLog.i(LOG, "Search success products: " + response.body().getProductos().size());
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                search.postValue(Resource.error(application.getString(R.string.search_error),null));
                MyLog.e(LOG,"Search error: "+t.getMessage());
            }
        });
    }

    public LiveData<Resource<Search>> searchValues(){
        return this.search;
    }
}
