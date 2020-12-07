package com.example.restaurants

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ResultsActivity : AppCompatActivity() {
    // api key
    private val apiKey = "2QNpcgw1hKgC-FpZ8mPqkGvqSZMkZ9UrbSlb9u6P7bmH3zZkzPVbZIrX" +
            "c9ZQ99_34gb7-l_mYulD7Ng6deK69nficPZlC7DGY60vPXkkam97cgO3b9mPwe82mhnFX3Yx"
    // gps stuff
    private lateinit var locationManager : LocationManager
    private lateinit var locationGps : Location
    private var hasGps = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // received from intent
        val searchTerm = intent.getStringExtra("foodSearch")
        val preferredRadius = intent.getIntExtra("mileRadius", -1)
        val preferredPrice = intent.getIntExtra("pricePref", -1)

        // keep a list of all the restaurants
        val restaurants = mutableListOf<Restaurant>()
        yelp_recyclerview.layoutManager = LinearLayoutManager(this)
        // instantiate an adapter to use in callback function
        val yelpAdapter = YelpAdapter(this, restaurants) {
            position ->
                val intent = Intent(this, RestaurantActivity::class.java)
                intent.putExtra("name", restaurants[position].name)
                intent.putExtra("rating", restaurants[position].rating.toString())
                intent.putExtra("price", restaurants[position].price)
                intent.putExtra("distance",
                    (Math.round(restaurants[position].distance/ 1610 * 10.0) /
                            10.0).toString() + " miles")
                intent.putExtra("review_count",
                    restaurants[position].num_reviews.toString()  + " ratings")
                intent.putExtra("category", restaurants[position].categories[0].title)
                intent.putExtra("location", restaurants[position].location.address)
                intent.putExtra("picture", restaurants[position].image)
                intent.putExtra("latitude", restaurants[position].coordinates.latitude)
                intent.putExtra("longitude", restaurants[position].coordinates.longitude)
                startActivity(intent)
        }
        yelp_recyclerview.adapter = yelpAdapter

        // retrofit helps with reading json data stuff
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.yelp.com/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val yelp = retrofit.create(YelpActivity::class.java)

        // gps stuff
        getLocation()
        val latitude = locationGps.latitude
        val longitude = locationGps.longitude

        // if no preferred price
        if (preferredPrice == -1) {
            // if preferred radius
            if (preferredRadius != -1) {
                yelp.lookupRestaurants(
                    "Bearer $apiKey", searchTerm!!,
                        latitude, longitude, preferredRadius)
                    .enqueue(object : Callback<YelpData> {
                        override fun onResponse(call: Call<YelpData>, response: Response<YelpData>) {
                            restaurants.addAll(response.body()?.restaurants!!)
                            yelpAdapter.notifyDataSetChanged()
                        }
                        override fun onFailure(call: Call<YelpData>, t: Throwable) {
                            Log.i("main", "onFailure $t")
                        }
                    })
            } else { // no preferred radius either
                yelp.lookupRestaurants(
                    "Bearer $apiKey", searchTerm!!, latitude, longitude)
                    .enqueue(object : Callback<YelpData> {
                        override fun onResponse(call: Call<YelpData>, response: Response<YelpData>) {
                            restaurants.addAll(response.body()?.restaurants!!)
                            yelpAdapter.notifyDataSetChanged()
                        }
                        override fun onFailure(call: Call<YelpData>, t: Throwable) {
                            Log.i("main", "onFailure $t")
                        }
                    })
            }
        } else if (preferredRadius == -1) { // has no preferred radius but has price
            yelp.lookupRestaurants(
                "Bearer $apiKey", latitude, longitude, preferredPrice, searchTerm!!)
                .enqueue(object : Callback<YelpData> {
                    override fun onResponse(call: Call<YelpData>, response: Response<YelpData>) {
                        restaurants.addAll(response.body()?.restaurants!!)
                        yelpAdapter.notifyDataSetChanged()
                    }
                    override fun onFailure(call: Call<YelpData>, t: Throwable) {
                        Log.i("main", "onFailure $t")
                    }
                })
        } else { // has a preferred radius and price
            yelp.lookupRestaurants(
                "Bearer $apiKey", searchTerm!!, latitude, longitude,
                    preferredRadius, preferredPrice)
                .enqueue(object : Callback<YelpData> {
                    override fun onResponse(call: Call<YelpData>, response: Response<YelpData>) {
                        restaurants.addAll(response.body()?.restaurants!!)
                        yelpAdapter.notifyDataSetChanged()
                    }
                    override fun onFailure(call: Call<YelpData>, t: Throwable) {
                        Log.i("main", "onFailure $t")
                    }
                })
        }
    }
    @SuppressLint("MissingPermission")
    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (hasGps) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                5000, 0F,
                object : LocationListener {
                    override fun onLocationChanged(p0: Location) {
                        if (p0 != null) {
                            locationGps = p0
                        }
                    }
                })
        }
        val localGpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        if (localGpsLocation != null) {
            locationGps = localGpsLocation
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // which menu item u picking
        when (item.itemId) {
            R.id.main_menu -> {
                val it = Intent(this, MainActivity::class.java)
                startActivity(it)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}