package com.example.cleanarchitecturedemo.feature.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cleanarchitecturedemo.databinding.ActivityMainBinding

class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
