package com.example.restaurants

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    // api key
    private val api_key = "2QNpcgw1hKgC-FpZ8mPqkGvqSZMkZ9UrbSlb9u6P7bmH3zZkzPVbZIrX" +
            "c9ZQ99_34gb7-l_mYulD7Ng6deK69nficPZlC7DGY60vPXkkam97cgO3b9mPwe82mhnFX3Yx"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // keep a list of all the restaurants
        val restaurants = mutableListOf<Restaurant>()
        yelp_recyclerview.layoutManager = LinearLayoutManager(this)
        // instantiate an adapter to use in callback function
        val yelp_adapter = YelpAdapter(this, restaurants)
        yelp_recyclerview.adapter = yelp_adapter
        // retrofit helps with reading json data stuff
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.yelp.com/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val yelp = retrofit.create(YelpActivity::class.java)
        yelp.lookupRestaurants("Bearer $api_key","Omakase", "Honolulu")
            .enqueue(object : Callback<YelpData> {
            override fun onResponse(call: Call<YelpData>, response: Response<YelpData>) {
                    restaurants.addAll(response.body()?.restaurants!!)
                    yelp_adapter.notifyDataSetChanged()
                }
                override fun onFailure(call: Call<YelpData>, t: Throwable) {
                    Log.i("main", "onFailure $t")
                }
            })
    }

}