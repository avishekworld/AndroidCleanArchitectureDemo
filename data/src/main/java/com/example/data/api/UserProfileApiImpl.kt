package com.example.data.api

import com.example.domain.api.UserProfileApi
import com.example.domain.models.UserProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext



class UserProfileApiImpl : UserProfileApi {
    override suspend fun getUserProfile(id: Long): UserProfile = withContext(Dispatchers.IO){
        delay(5000)
        UserProfile(id, "John Mark", "john@dev.com")
    }
}