package com.example.restaurants

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
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
                    "Reset!", DialogInterface.OnClickListener { dialog, id ->
                        pref_mile.text = "Mile Radius (optional)"
                    })
                builder.setNegativeButton(
                    "Oh, heck no.", DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(this,
                            "ok, then stop clicking me.",
                            Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    })
                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()
            }
        }
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
                    "Reset!", DialogInterface.OnClickListener { dialog, id ->
                        pref_price.text = "How many $? (optional)"
                    })
                builder.setNegativeButton(
                    "Oh, heck no.", DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(this,
                            "ok, then stop clicking me.",
                            Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    })
                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()
            }
        }
        done.setOnClickListener {
            if (pref_food.text.toString() == "") {
                Toast.makeText(this,
                    "Hey, what do you want to search?!",
                    Toast.LENGTH_SHORT).show()
            } else {
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