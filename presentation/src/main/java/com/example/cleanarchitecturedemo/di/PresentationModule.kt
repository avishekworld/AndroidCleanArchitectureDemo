package com.example.cleanarchitecturedemo.di

import com.example.cleanarchitecturedemo.viewmodels.OnBoardViewModel
import com.example.cleanarchitecturedemo.viewmodels.UseProfileViewModel
import com.example.cleanarchitecturedemo.views.navigation.NavigationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        UseProfileViewModel(get())
    }

    viewModel {
        OnBoardViewModel(get())
    }

    viewModel {
        NavigationViewModel(androidContext())
    }
}
