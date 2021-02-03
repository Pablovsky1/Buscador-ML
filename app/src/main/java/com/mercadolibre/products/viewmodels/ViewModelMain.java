package com.mercadolibre.products.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mercadolibre.products.repositories.SearchRepository;
import com.mercadolibre.products.models.search.Search;
import com.mercadolibre.products.util.Resource;

public class ViewModelMain extends AndroidViewModel {

    private final SearchRepository searchRepository;

    public ViewModelMain(@NonNull Application application) {
        super(application);
        this.searchRepository = new SearchRepository(application);
    }

    public void startSearch(String item, int offset, int limit){
        searchRepository.getSearch(item,offset,limit);
    }

    public LiveData<Resource<Search>> getSearch(){
        return this.searchRepository.searchValues();
    }



}
