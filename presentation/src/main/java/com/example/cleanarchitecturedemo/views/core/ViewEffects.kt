package com.example.cleanarchitecturedemo.views.core

sealed class ViewEffects {
    object ShowProcessingView : ViewEffects()
    object HideProcessingView : ViewEffects()
    object Idle : ViewEffects()
}
