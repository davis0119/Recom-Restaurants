package com.example.restaurants

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_restaurant.*

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
            Audio.pauseAudio()
            val i = Intent(this, MapsActivity::class.java)
            i.putExtra("name", intent.getStringExtra("name"))
            i.putExtra("address", intent.getStringExtra("location"))
            i.putExtra("latitude", intent.getDoubleExtra("latitude", 0.0))
            i.putExtra("longitude", intent.getDoubleExtra("longitude", 0.0))
            startActivity(i)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // which menu item u picking
        return when (item.itemId) {
            R.id.main_menu -> {
                Audio.pauseAudio()
                val it = Intent(this, MainActivity::class.java)
                startActivity(it)
                true
            }
            R.id.search -> {
                Audio.pauseAudio()
                val it = Intent(this, SearchActivity::class.java)
                startActivity(it)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}