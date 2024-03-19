package com.example.quicknews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    String base_url="https://newsapi.org/v2/";
    @GET("top-headlines")
    Call<MainNews> getNews(
            @Query("country") String country,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String apiKey

    );
    @GET("top-headlines")
    Call<MainNews> getCategory(
            @Query("country") String country,
            @Query("category") String category,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String apiKey

    );



}
