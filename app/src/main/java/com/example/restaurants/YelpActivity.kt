package com.example.restaurants

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

public interface YelpActivity {

    @GET("businesses/search")
    fun lookupRestaurants(
        @Header("Authorization") authHeader: String,
        @Query("term") searchTerm : String,
        @Query("location") location : String) : Call<YelpData>

}