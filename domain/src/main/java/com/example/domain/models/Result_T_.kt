package com.example.domain.models


sealed class Result<T> {
    data class Success<T>(val t : T) : Result<T>()
    data class Failure<T>(val t : Throwable) : Result<T>()
}
