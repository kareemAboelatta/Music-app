package com.example.mazika.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cuberto.liquid_swipe.LiquidPager
import com.example.mazika.R
import com.example.mazika.adapters.MyFragmentPagerAdapter
import com.example.mazika.ui.MainActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val pager = findViewById<LiquidPager>(R.id.pager)
        pager.adapter = MyFragmentPagerAdapter(supportFragmentManager)


        tv_skip.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}