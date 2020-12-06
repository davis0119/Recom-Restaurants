package com.example.restaurants

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        done.setOnClickListener {
            if (pref_food.text.toString() == "") {
                Toast.makeText(this,
                    "Hey, what do you want to search?!",
                    Toast.LENGTH_SHORT).show()
            } else {
                val i = Intent(this@SearchActivity, ResultsActivity::class.java)
                i.putExtra("foodSearch", pref_food.text.toString())
                if (pref_mile.text.toString() != "") {
                    i.putExtra("mileRadius", pref_mile.text.toString().toInt() * 1610)
                }
                if (pref_price.text.toString() != "") {
                    i.putExtra("pricePref", pref_price.text.toString().toInt())
                }
                startActivity(i)
            }
        }
    }
}