package com.mercadolibre.products.retrofit;

import com.mercadolibre.products.models.details.Item;
import com.mercadolibre.products.models.search.Search;
import com.mercadolibre.products.models.suggest.Suggest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiRequest {

    @GET("sites/MLA/search")
    Call<Search> getItems(@Query("q") String query, @Query("offset") Integer offset, @Query("limit") Integer limit);


    @GET("sites/MLA/autosuggest")
    Call<Suggest> getSuggest(@Query("q") String query, @Query("limit") Integer limit,@Query("showFilters") Boolean showFilters);

    @GET("items/{id}")
    Call<Item> getProduct(@Path("id") String id);

}
