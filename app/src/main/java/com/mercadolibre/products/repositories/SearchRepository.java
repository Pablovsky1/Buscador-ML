package com.mercadolibre.products.repositories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mercadolibre.products.R;
import com.mercadolibre.products.models.search.Search;
import com.mercadolibre.products.models.search.SearchPagination;
import com.mercadolibre.products.models.search.SearchProduct;
import com.mercadolibre.products.retrofit.ApiRequest;
import com.mercadolibre.products.retrofit.RetrofitRequest;
import com.mercadolibre.products.util.AppConstant;
import com.mercadolibre.products.util.MyLog;
import com.mercadolibre.products.util.Resource;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchRepository {
    private final String LOG = "SearchRepository";
    private final Application application;
    private final Retrofit retrofit;
    private final MutableLiveData<Resource<Search>> search;
    private final MutableLiveData<ArrayList<SearchProduct>> products;
    private final MutableLiveData<SearchPagination> pagination;
    private boolean loading;

    public SearchRepository(Application application) {
        this.application = application;
        this.retrofit = RetrofitRequest.getRetrofitInstance();
        this.search = new MutableLiveData<>();
        this.products = new MutableLiveData<>();
        this.pagination = new MutableLiveData<>();
        this.loading = true;
    }

    public void getSearch(String item){
        this.search.setValue(Resource.loading(null));
        removeProducts();
        ApiRequest apiRequest = retrofit.create(ApiRequest.class);
        Call<Search> call = apiRequest.getItems(item, AppConstant.DEFAULT_OFFSET, AppConstant.PAGE_LIMIT);
        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(@NonNull Call<Search> call,@NonNull Response<Search> response) {
                if(response.body()==null || response.body().getProductos().size()<=0){
                    search.postValue(Resource.error(application.getString(R.string.search_not_found)+"\n"+application.getString(R.string.search_not_found_advice),null));
                    MyLog.e(LOG,"Search null");
                }else {
                    search.postValue(Resource.success(response.body()));
                    addProducts(response.body().getProductos());
                    pagination.setValue(response.body().getPaging());
                    MyLog.i(LOG, "Search success products: " + response.body().getProductos().size());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Search> call,@NonNull Throwable t) {
                search.postValue(Resource.error(application.getString(R.string.search_error),null));
                MyLog.e(LOG,"Search error: "+t.getMessage());
            }
        });
    }

    public void loadProducts(String item){
        if(!loading)return;
        if(pagination.getValue()==null || pagination.getValue().getOffset()==null)return;
        int offset = pagination.getValue().getOffset()+AppConstant.PAGE_LIMIT+1;

        ApiRequest apiRequest = retrofit.create(ApiRequest.class);
        Call<Search> call = apiRequest.getItems(item, offset, AppConstant.PAGE_LIMIT);
        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(@NonNull Call<Search> call,@NonNull Response<Search> response) {
                if(response.body()==null || response.body().getProductos().size()<=0){
                    MyLog.e(LOG,"Search null");
                    loading=true;
                }else {
                    pagination.setValue(response.body().getPaging());
                    ArrayList<SearchProduct> searchProducts = products.getValue();
                    if(searchProducts==null){
                        MyLog.e(LOG,"SearchProducts null");
                        loading=true;
                        return;
                    }
                    searchProducts.addAll(response.body().getProductos());
                    addProducts(searchProducts);
                    loading=true;
                    MyLog.i(LOG, "Search success products: " + response.body().getProductos().size());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Search> call,@NonNull Throwable t) {
                loading=true;
                MyLog.e(LOG,"Search error: "+t.getMessage());
            }
        });
    }

    public LiveData<ArrayList<SearchProduct>> getProducts(){
        return this.products;
    }

    public void removeProducts(){
        products.postValue(new ArrayList<>());
    }
    public void addProducts(ArrayList<SearchProduct> list){
        products.postValue(list);
    }

    public LiveData<Resource<Search>> searchValues(){
        return this.search;
    }
}
