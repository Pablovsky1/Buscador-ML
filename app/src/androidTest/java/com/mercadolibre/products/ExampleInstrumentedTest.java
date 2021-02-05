package com.mercadolibre.products;

import android.app.Application;
import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.mercadolibre.products.repositories.ProductRepository;
import com.mercadolibre.products.repositories.SearchRepository;
import com.mercadolibre.products.repositories.SuggestRepository;
import com.mercadolibre.products.ui.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.mercadolibre.products", appContext.getPackageName());

    }
    @Test
    public void productRepository(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ProductRepository productRepository = new ProductRepository(appContext);
        String s = null;
        productRepository.getProduct("test");
        productRepository.getProduct(s);
    }

    @Test
    public void searchRepository(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SearchRepository searchRepository = new SearchRepository(appContext);
        searchRepository.getSearch("test");
        searchRepository.getSearch(null);
        searchRepository.loadProducts("test");
        searchRepository.loadProducts(null);
    }

    @Test
    public void suggestRepository(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SuggestRepository suggestRepository = new SuggestRepository(appContext);
        suggestRepository.getSuggest(null);
        suggestRepository.getSuggest("test");
    }



}