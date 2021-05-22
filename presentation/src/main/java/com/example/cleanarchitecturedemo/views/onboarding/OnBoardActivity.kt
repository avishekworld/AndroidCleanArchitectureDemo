package com.example.cleanarchitecturedemo.views.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.cleanarchitecturedemo.databinding.ActivityOnBoardBinding

class OnBoardActivity : AppCompatActivity() {
    private lateinit var viewPager : ViewPager2
    private lateinit var viewPagerAdapter : OnBoardingViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOnBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewPager = binding.viewPager
        viewPager.setPageTransformer(ZoomOutPageTransformer())
        viewPagerAdapter = OnBoardingViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter
    }
}