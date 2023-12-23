package com.example.mazika.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cuberto.liquid_swipe.LiquidPager
import com.example.mazika.R
import com.example.mazika.adapters.MyFragmentPagerAdapter
import com.example.mazika.databinding.ActivityMainBinding
import com.example.mazika.ui.MainActivity
import com.example.mazika.databinding.ActivitySplashScreenBinding


class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val pager = findViewById<LiquidPager>(R.id.pager)
        pager.adapter = MyFragmentPagerAdapter(supportFragmentManager)


        binding.tvSkip.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}