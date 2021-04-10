package com.example.cleanarchitecturedemo.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.fixedRateTimer


class MainViewModel : ViewModel() {

    private val TAG = "MainViewModel"

    private val _uiState = MutableLiveData<Int>()
    val uiState = _uiState
    private var number = 0

    init {
        fixedRateTimer("ViewModelThread", true, period = 1000) {
          number++
          uiState.postValue(number)
          Log.d(TAG, "uiState update $number")
        }
    }

}