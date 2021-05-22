package com.example.data.api

import com.example.data.model.OnBoardData

interface OnBoardDataRepository {
    suspend fun getData(id : Int) : OnBoardData
}

class OnBoardDataRepositoryImpl(private val api : OnBoardDataApi) : OnBoardDataRepository {
    override suspend fun getData(id: Int): OnBoardData {
        return api.getData(id)
    }
}