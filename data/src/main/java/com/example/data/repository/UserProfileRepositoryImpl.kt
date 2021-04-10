package com.example.data.repository

import com.example.data.cache.UserProfileCache
import com.example.domain.api.UserProfileApi
import com.example.domain.models.UserProfile
import com.example.domain.repository.UserProfileRepository



class UserProfileRepositoryImpl(
    private val userProfileCache: UserProfileCache,
    private val userProfileApi: UserProfileApi
) : UserProfileRepository {
    override suspend fun getUserProfile(id: Long): UserProfile {
        return userProfileCache.getUserProfile(id)
            ?: userProfileApi.getUserProfile(id).let { userProfile ->
                userProfileCache.addUserProfile(id, userProfile)
                requireNotNull(userProfileCache.getUserProfile(id))
            }
    }
}