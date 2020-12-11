package com.example.restaurants

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
class SearchActivity : AppCompatActivity() {
    lateinit var mp: MediaPlayer
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        // music
        Audio.playAudio(this, R.raw.fortree_city)
        // increase mile preference
        inc_miles.setOnClickListener {
            if (pref_mile.text.toString() == "Mile Radius (optional)") {
                pref_mile.text = "1"
            } else if (pref_mile.text.toString() != "24") {
                pref_mile.text = (pref_mile.text.toString().toInt() + 1).toString()
            } else {
                Toast.makeText(this,
                    "Max mile radius allowed.",
                    Toast.LENGTH_SHORT).show()
            }
        }
        // decrease mile preference
        dec_miles.setOnClickListener {
            if (pref_mile.text.toString() == "Mile Radius (optional)") {
                pref_mile.text = "1"
            } else if (pref_mile.text.toString() != "1") {
                pref_mile.text = (pref_mile.text.toString().toInt() - 1).toString()
            } else {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Reset Miles")
                builder.setMessage("Do you want to let us handle the mile radius?")
                builder.setPositiveButton(
                    "Reset!"
                ) { dialog, id ->
                    pref_mile.text = "Mile Radius (optional)"
                }
                builder.setNegativeButton(
                    "Oh, heck no."
                ) { dialog, id ->
                    Toast.makeText(this,
                        "ok, then stop clicking me.",
                        Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()
            }
        }
        // increase price preference
        inc_price.setOnClickListener {
            if (pref_price.text.toString() == "How many $? (optional)") {
                pref_price.text = "1"
            } else if (pref_price.text.toString() != "4") {
                pref_price.text = (pref_price.text.toString().toInt() + 1).toString()
            } else {
                Toast.makeText(this,
                    "Max mile radius allowed.",
                    Toast.LENGTH_SHORT).show()
            }
        }
        // decrease price
        dec_price.setOnClickListener {
            if (pref_price.text.toString() == "How many $? (optional)") {
                pref_price.text = "1"
            } else if (pref_price.text.toString() != "1") {
                pref_price.text = (pref_price.text.toString().toInt() - 1).toString()
            } else {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Reset Price")
                builder.setMessage("Do you want to let us handle the price levels?")
                builder.setPositiveButton(
                    "Reset!"
                ) { dialog, id ->
                    pref_price.text = "How many $? (optional)"
                }
                builder.setNegativeButton(
                    "Oh, heck no."
                ) { dialog, id ->
                    Toast.makeText(this,
                        "ok, then stop clicking me.",
                        Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()
            }
        }
        // look for it!
        done.setOnClickListener {
            if (pref_food.text.toString() == "") {
                Toast.makeText(this,
                    "Hey, what do you want to search?!",
                    Toast.LENGTH_SHORT).show()
            } else {
                Audio.pauseAudio()
                val i = Intent(this@SearchActivity, ResultsActivity::class.java)
                i.putExtra("foodSearch", pref_food.text.toString())
                if (pref_mile.text.toString() != "Mile Radius (optional)") {
                    i.putExtra("mileRadius", pref_mile.text.toString().toInt() * 1610)
                }
                if (pref_price.text.toString() != "How many $? (optional)") {
                    i.putExtra("pricePref", pref_price.text.toString().toInt())
                }
                startActivity(i)
            }
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
                Toast.makeText(this,
                    "You're already here, stop playing around.",
                    Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}