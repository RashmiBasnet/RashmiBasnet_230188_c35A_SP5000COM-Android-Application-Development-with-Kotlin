package com.example.outfitternepal.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.outfitternepal.R
import com.example.outfitternepal.databinding.ActivityTripsBinding
import com.example.outfitternepal.ui.activity.BookActivity

class TripsActivity : AppCompatActivity() {

    lateinit var binding: ActivityTripsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trips)

        binding = ActivityTripsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bookNowBtn2: Button = findViewById(R.id.bookNowBtn2)
        bookNowBtn2.setOnClickListener {
            val intent = Intent(this, BookActivity::class.java)
            startActivity(intent)
        }

        // Handle other button clicks and navigation here
    }
}