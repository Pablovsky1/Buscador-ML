package com.mercadolibre.products.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mercadolibre.products.models.details.Item;
import com.mercadolibre.products.models.details.ItemAttributes;
import com.mercadolibre.products.models.details.ItemPictures;
import com.mercadolibre.products.repositories.ProductRepository;
import com.mercadolibre.products.util.Resource;

import java.util.ArrayList;

public class ViewModelProduct extends AndroidViewModel {

    private final ProductRepository repository;

    public ViewModelProduct(@NonNull Application application) {
        super(application);
        this.repository = new ProductRepository(application);
    }

    public void loadProduct(String id){
        repository.getProduct(id);
    }

    public LiveData<Resource<Item>> getProduct(){
        return repository.getProduct();
    }

    public LiveData<ArrayList<ItemAttributes>> getAttributes(){
        return repository.getAttributes();
    }
    public LiveData<ArrayList<ItemPictures>> getPictures(){
        return repository.getPictures();
    }

}
