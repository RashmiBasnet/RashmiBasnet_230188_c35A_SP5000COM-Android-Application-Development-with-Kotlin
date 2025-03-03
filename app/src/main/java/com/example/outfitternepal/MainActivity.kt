package com.example.outfitternepal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.outfitternepal.databinding.ActivityMainBinding
import com.example.outfitternepal.ui.activity.BookActivity
import com.example.outfitternepal.ui.activity.ContactActivity
import com.example.outfitternepal.ui.activity.TripsActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle Book Now Button Click
        val bookNowButton: Button = findViewById(R.id.bookNowButton)
        bookNowButton.setOnClickListener {
            val intent = Intent(this, BookActivity::class.java)
            startActivity(intent)
        }

        // Handle View All Button Click
        val viewAllButton: Button = findViewById(R.id.viewAllButton)
        viewAllButton.setOnClickListener {
            val intent = Intent(this, TripsActivity::class.java)
            startActivity(intent)
        }

        // Handle Contact Us Button Click
        val contactButton: Button = findViewById(R.id.contactButton)
        contactButton.setOnClickListener {
            val intent = Intent(this, ContactActivity::class.java)
            startActivity(intent)
        }
    }
}