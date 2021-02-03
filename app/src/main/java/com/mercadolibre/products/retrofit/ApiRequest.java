package com.mercadolibre.products.retrofit;

import com.mercadolibre.products.models.search.Search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {

    @GET("sites/MLA/search")
    Call<Search> getItems(@Query("q") String query, @Query("offset") Integer offset, @Query("limit") Integer limit);
}
