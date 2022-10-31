package com.example.cleanarchitecturedemo.feature.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.ui.compose.theme.AppBrandTheme
import org.koin.android.ext.android.inject

class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val viewState = viewModel.viewState.collectAsState()

                AppBrandTheme {
                    WeatherScreen(viewState = viewState.value)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.handleEvent(WeatherViewEvent.Init)
    }
}
