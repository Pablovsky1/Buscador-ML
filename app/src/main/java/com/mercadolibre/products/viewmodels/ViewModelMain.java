package com.mercadolibre.products.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mercadolibre.products.models.search.SearchProduct;
import com.mercadolibre.products.repositories.SearchRepository;
import com.mercadolibre.products.models.search.Search;
import com.mercadolibre.products.util.Resource;

import java.util.ArrayList;
import java.util.List;

public class ViewModelMain extends AndroidViewModel {

    private final SearchRepository searchRepository;

    public ViewModelMain(@NonNull Application application) {
        super(application);
        this.searchRepository = new SearchRepository(application);
    }

    public LiveData<ArrayList<SearchProduct>> getProducts(){
        return searchRepository.getProducts();
    }

    public void startSearch(String item){
        searchRepository.getSearch(item);
    }

    public void loadProducts(String item){
        searchRepository.loadProducts(item);
    }

    public LiveData<Resource<Search>> getSearch(){
        return this.searchRepository.searchValues();
    }



}
