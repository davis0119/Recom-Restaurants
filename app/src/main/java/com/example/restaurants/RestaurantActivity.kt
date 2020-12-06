package com.example.restaurants

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_restaurant.*
import kotlinx.android.synthetic.main.restaurant_item.view.*

class RestaurantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        if (intent.getStringExtra("name")!!.length > 37) {
            name.text = intent.getStringExtra("name")?.substring(0, 37) + "..."
        } else {
            name.text = intent.getStringExtra("name")
        }
        ratings.text = intent.getStringExtra("rating")
        priceLevel.text = intent.getStringExtra("price")
        distanceAway.text = intent.getStringExtra("distance")
        numRatings.text = intent.getStringExtra("review_count")
        category.text = intent.getStringExtra("category")
        address.text = intent.getStringExtra("location")
        Picasso.get()
            .load(intent.getStringExtra("picture")) // load the image
            .centerCrop().resize(150, 150) // crop to fit image view
            .into(picture) // inject that thing!
        submitRestaurant.setOnClickListener {
            val i = Intent(this, MapsActivity::class.java)
            i.putExtra("name", intent.getStringExtra("name"))
            i.putExtra("latitude", intent.getDoubleExtra("latitude", 0.0))
            i.putExtra("longitude", intent.getDoubleExtra("longitude", 0.0))
            startActivity(i)
        }
    }

}