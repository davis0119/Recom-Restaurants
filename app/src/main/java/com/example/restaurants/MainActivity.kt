package com.example.restaurants

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // music
        Audio.playAudio(this, R.raw.littleroot_town)

        search.setOnClickListener {
            val i = Intent(this, SearchActivity::class.java)
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
                Toast.makeText(this,
                    "You're already here, stop playing around.",
                    Toast.LENGTH_SHORT).show()
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