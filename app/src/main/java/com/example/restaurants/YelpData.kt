package com.example.restaurants

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName

data class YelpData (
    @SerializedName("total") val total : Int,
    @SerializedName("businesses") val restaurants : List<Restaurant>
)

data class Category (
    @SerializedName("title") val title : String
)

data class Location (
    @SerializedName("address1") val address : String
)

data class Restaurant (
    @SerializedName("name") val name : String,
    @SerializedName("price") val price : String,
    @SerializedName("rating") val rating : Double,
    @SerializedName("review_count") val num_reviews : Int,
    @SerializedName("location") val location : Location,
    @SerializedName("distance") val distance : Double,
    @SerializedName("image_url") val image : String,
    @SerializedName("categories") val categories : List<Category>,
    @SerializedName("coordinates") val coordinates : LatLng
)