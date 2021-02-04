package com.mercadolibre.products.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mercadolibre.products.models.suggest.SuggestQueries;
import com.mercadolibre.products.repositories.SuggestRepository;

import java.util.ArrayList;

public class ViewModelSearch extends AndroidViewModel {

    private SuggestRepository suggestRepository;

    public ViewModelSearch(@NonNull Application application) {
        super(application);
        suggestRepository = new SuggestRepository(application);
    }

    public void searchSuggest(String item){
        this.suggestRepository.getSuggest(item);
    }

    public LiveData<ArrayList<SuggestQueries>> getSuggest(){
        return suggestRepository.getSuggest();
    }



}
