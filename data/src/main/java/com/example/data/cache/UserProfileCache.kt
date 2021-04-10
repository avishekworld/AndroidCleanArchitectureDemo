package com.example.data.cache

import com.example.domain.models.UserProfile



interface UserProfileCache {
    suspend fun getUserProfile(id : Long) : UserProfile?
    suspend fun addUserProfile(id : Long, userProfile: UserProfile)
}

class UserProfileCacheInMemory : UserProfileCache {
    private val map = HashMap<Long, UserProfile>()

    override suspend fun getUserProfile(id: Long): UserProfile? = map[id]

    override suspend fun addUserProfile(id: Long, userProfile: UserProfile) {
        map[id] = userProfile
    }
}