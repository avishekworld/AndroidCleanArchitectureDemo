package com.example.domain.usecases

import com.example.domain.models.UserProfile
import com.example.domain.repository.UserProfileRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking


class GetUseProfileUseCaseTest : TestCase() {


    fun testRun() = runBlocking {
        val userProfile = mockk<UserProfile>()
        val repository = mockk<UserProfileRepository> {
            coEvery {
                getUserProfile(any())
            } returns userProfile
        }
        val useCase = GetUseProfileUseCase(repository)
        assertEquals(userProfile, useCase.run(123))
    }
}