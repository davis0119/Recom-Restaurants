package com.example.restaurants

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.restaurant_item.view.*
import kotlin.math.roundToInt

class YelpAdapter (
    private val activity : Activity,
    val restaurants : List<Restaurant>,
    private var getPosition:(Int) -> Unit) :
    RecyclerView.Adapter<YelpAdapter.RestaurantViewHolder>() {

    inner class RestaurantViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(restaurant : Restaurant, position: Int, getPosition: (Int) -> Unit) {
            if (restaurant.name.length > 30) {
                itemView.restName.text = restaurant.name.subSequence(0, 30).toString() + "..."
            } else {
                itemView.restName.text = restaurant.name
            }
            itemView.restAddress.text = restaurant.location.address
            itemView.restRating.text = restaurant.rating.toString() + " / 5.0 Stars |"
            itemView.restDistance.text =
                ((restaurant.distance / 1610 * 10.0).roundToInt() / 10.0).toString() + " miles"
            itemView.restNumRatings.text = restaurant.num_reviews.toString() + " ratings"
            itemView.restPrice.text = restaurant.price
            itemView.restCategory.text = restaurant.categories[0].title
            Picasso.get()
                .load(restaurant.image) // load the image
                .centerCrop().resize(80, 80) // crop to fit image view
                .into(itemView.restPic) // inject that thing!
            // sets up the onClickListener to the entire view
            itemView.setOnClickListener {
                getPosition(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder =
        RestaurantViewHolder(LayoutInflater.from(activity)
            .inflate(R.layout.restaurant_item, parent, false))

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(restaurants[position], position, getPosition)
    }

    override fun getItemCount() = restaurants.size

}