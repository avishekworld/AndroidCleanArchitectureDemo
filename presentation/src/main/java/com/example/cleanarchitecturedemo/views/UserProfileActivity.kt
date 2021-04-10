package com.example.cleanarchitecturedemo.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cleanarchitecturedemo.databinding.ActivityUserProfileBinding
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
class UserProfileActivity : AppCompatActivity() {

    private val TAG = "UserProfileActivity"

    private lateinit var binding : ActivityUserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}