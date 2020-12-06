package com.example.restaurants

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface YelpActivity {

    // only wanna search something
    @GET("businesses/search")
    fun lookupRestaurants(
        @Header("Authorization") authorization: String,
        @Query("term") searchedTerm : String,
        @Query("latitude") latitude : Double,
        @Query("longitude") longitude : Double) : Call<YelpData>

    // only has preferred radius
    @GET("businesses/search")
    fun lookupRestaurants(
        @Header("Authorization") authorization: String,
        @Query("term") searchedTerm : String,
        @Query("latitude") latitude : Double,
        @Query("longitude") longitude : Double,
        @Query("radius") radius : Int) : Call<YelpData>

    // only has preferred price
    @GET("businesses/search")
    fun lookupRestaurants(
        @Header("Authorization") authorization: String,
        @Query("latitude") latitude : Double,
        @Query("longitude") longitude : Double,
        @Query("price") price : Int,
        @Query("term") searchedTerm : String) : Call<YelpData>

    // has both price and radius
    @GET("businesses/search")
    fun lookupRestaurants(
        @Header("Authorization") authorization: String,
        @Query("term") searchedTerm : String,
        @Query("latitude") latitude : Double,
        @Query("longitude") longitude : Double,
        @Query("radius") radius : Int,
        @Query("price") price : Int) : Call<YelpData>

}