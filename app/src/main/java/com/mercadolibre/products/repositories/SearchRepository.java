package com.mercadolibre.products.repositories;

import android.content.Context;

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
import com.mercadolibre.products.util.Resource;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchRepository {
    private final Context context;
    private final Retrofit retrofit;
    private final MutableLiveData<Resource<Search>> search;
    private final MutableLiveData<ArrayList<SearchProduct>> products;
    private final MutableLiveData<SearchPagination> pagination;
    private boolean loading;

    public SearchRepository(Context context) {
        this.context = context;
        this.retrofit = RetrofitRequest.getRetrofitInstance();
        this.search = new MutableLiveData<>();
        this.products = new MutableLiveData<>();
        this.pagination = new MutableLiveData<>();
        this.loading = true;
    }

    public void getSearch(String item){
        removeProducts();
        if(!AppConstant.isConnectionAvailable(context)){
            this.search.postValue(Resource.error(context.getString(R.string.conection),null));
            return;
        }
        this.search.postValue(Resource.loading(null));

        ApiRequest apiRequest = retrofit.create(ApiRequest.class);
        Call<Search> call = apiRequest.getItems(item, AppConstant.DEFAULT_OFFSET, AppConstant.PAGE_LIMIT);
        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(@NonNull Call<Search> call,@NonNull Response<Search> response) {
                if(response.body()==null || response.body().getProductos().size()<=0){
                    search.postValue(Resource.error(context.getString(R.string.search_not_found)+"\n"+context.getString(R.string.search_not_found_advice),null));
                }else {
                    search.postValue(Resource.success(response.body()));
                    addProducts(response.body().getProductos());
                    pagination.setValue(response.body().getPaging());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Search> call,@NonNull Throwable t) {
                search.postValue(Resource.error(context.getString(R.string.search_error),null));
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
                    loading=true;
                }else {
                    pagination.postValue(response.body().getPaging());
                    ArrayList<SearchProduct> searchProducts = products.getValue();
                    if(searchProducts==null){
                        loading=true;
                        return;
                    }
                    searchProducts.addAll(response.body().getProductos());
                    addProducts(searchProducts);
                    loading=true;
                }
            }

            @Override
            public void onFailure(@NonNull Call<Search> call,@NonNull Throwable t) {
                loading=true;
            }
        });
    }

    public LiveData<ArrayList<SearchProduct>> getProducts(){
        return this.products;
    }

    private void removeProducts(){
        products.postValue(new ArrayList<>());
    }
    private void addProducts(ArrayList<SearchProduct> list){
        products.postValue(list);
    }

    public LiveData<Resource<Search>> searchValues(){
        return this.search;
    }
}
