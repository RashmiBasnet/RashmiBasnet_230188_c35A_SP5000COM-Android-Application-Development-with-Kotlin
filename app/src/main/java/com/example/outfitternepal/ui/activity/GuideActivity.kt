package com.example.outfitternepal.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.outfitternepal.R
import com.example.outfitternepal.databinding.ActivityGuideBinding
import com.example.outfitternepal.ui.activity.BookActivity

class GuideActivity : AppCompatActivity() {

    lateinit var binding: ActivityGuideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)

        binding = ActivityGuideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle other button clicks and navigation here
    }
}