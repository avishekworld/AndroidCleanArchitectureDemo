package com.example.cleanarchitecturedemo.views.core

sealed class ViewState {
    object Show : ViewState()
    object Disabled : ViewState()
    object Hide : ViewState()
}
