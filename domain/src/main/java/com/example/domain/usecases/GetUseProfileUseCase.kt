package com.example.domain.usecases

import com.example.domain.core.SuspendUseCase
import com.example.domain.models.UserProfile
import com.example.domain.repository.UserProfileRepository


class GetUseProfileUseCase(private val userProfileRepository: UserProfileRepository) : SuspendUseCase<Long, UserProfile> {
    override suspend fun run(id: Long): UserProfile {
        return userProfileRepository.getUserProfile(id)
    }
}