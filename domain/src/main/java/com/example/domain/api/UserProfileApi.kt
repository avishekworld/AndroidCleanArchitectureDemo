package com.example.domain.api

import com.example.domain.models.UserProfile


interface UserProfileApi {
    suspend fun getUserProfile(id : Long) : UserProfile
}