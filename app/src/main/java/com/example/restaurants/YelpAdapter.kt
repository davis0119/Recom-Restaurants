package com.example.restaurants

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.restaurant_item.view.*

class YelpAdapter (val activity : Activity, val restaurants : List<Restaurant>) :
    RecyclerView.Adapter<YelpAdapter.RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder =
        RestaurantViewHolder(LayoutInflater.from(activity).inflate(R.layout.restaurant_item, parent, false))

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(restaurants[position])
    }

    override fun getItemCount() = restaurants.size;

    class RestaurantViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(restaurant : Restaurant) {
            itemView.restaurantName.text = restaurant.name
        }
    }

}