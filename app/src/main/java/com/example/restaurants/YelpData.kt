package com.example.restaurants

import com.google.gson.annotations.SerializedName

data class YelpData (
    @SerializedName("total") val total : Int,
    @SerializedName("businesses") val restaurants : List<Restaurant>
)

data class Restaurant (
    val name : String,
    val rating : Double,
    val price : String,
    @SerializedName("distance") val distance : Double,
    @SerializedName("review_count") val num_reviews : Int,
    @SerializedName("image_url") val image : String,
    val categories : List<Category>,
    val location : Location
)

data class Category (
    val title : String
)

data class Location (
    @SerializedName("address1") val address : String
)