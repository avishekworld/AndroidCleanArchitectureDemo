package com.example.domain.repository

import com.example.domain.models.UserProfile

interface UserProfileRepository {
    suspend fun getUserProfile(id: Long): UserProfile
}
