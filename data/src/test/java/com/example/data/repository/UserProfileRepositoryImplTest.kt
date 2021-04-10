package com.example.data.repository

import com.example.data.cache.UserProfileCache
import com.example.domain.api.UserProfileApi
import com.example.domain.models.UserProfile
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking



class UserProfileRepositoryImplTest : TestCase() {

    fun testGetUserProfileCache() = runBlocking {
        val userId : Long = 123
        val userProfile = mockk<UserProfile>()
        val userProfileCache = mockk<UserProfileCache> {
            coEvery {
                getUserProfile(userId)
            } returns userProfile
        }
        val userProfileApi = mockk<UserProfileApi>(relaxed = true)
        val userProfileRepository = UserProfileRepositoryImpl(userProfileCache, userProfileApi)
        assertEquals(userProfile, userProfileRepository.getUserProfile(userId))
        coVerify {
            userProfileCache.getUserProfile(userId)
        }
        coVerify (exactly = 0){
            userProfileApi.getUserProfile(userId)
        }
    }

    fun testGetUserProfileApi() = runBlocking {
        val userId : Long = 123
        val userProfile = mockk<UserProfile>()
        val userProfileCache = mockk<UserProfileCache>(relaxed = true) {
            coEvery {
                getUserProfile(userId)
            } returns null andThen userProfile
        }
        val userProfileApi = mockk<UserProfileApi>(relaxed = true) {
            coEvery {
                getUserProfile(userId)
            } returns userProfile
        }
        val userProfileRepository = UserProfileRepositoryImpl(userProfileCache, userProfileApi)
        assertEquals(userProfile, userProfileRepository.getUserProfile(userId))
        coVerify {
            userProfileApi.getUserProfile(userId)
            userProfileCache.addUserProfile(userId, userProfile)
        }
        coVerify (exactly = 2){
            userProfileCache.getUserProfile(userId)
        }
    }
}