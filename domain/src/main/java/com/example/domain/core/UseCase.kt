package com.example.domain.core


interface UseCase<Params, Result>

interface SuspendUseCase<Params, Result> : UseCase<Params, Result> {
    suspend fun run(p : Params) : Result
}