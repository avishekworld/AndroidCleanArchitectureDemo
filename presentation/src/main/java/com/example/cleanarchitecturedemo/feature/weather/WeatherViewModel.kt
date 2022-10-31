package com.example.cleanarchitecturedemo.feature.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecturedemo.views.core.ViewEffects
import com.example.cleanarchitecturedemo.views.core.ViewState
import com.example.domain.logger.Logger
import com.example.domain.models.Result
import com.example.domain.weather.Geolocation
import com.example.domain.weather.GetWeatherUseCase
import com.example.domain.weather.WeatherDetails
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val logger: Logger
) : ViewModel() {

    private val interViewState = MutableStateFlow<WeatherViewState>(WeatherViewState())
    val viewState: StateFlow<WeatherViewState>
        get() = interViewState

    private val internalViewEffect = MutableStateFlow<ViewEffects>(ViewEffects.Idle)
    val viewEffects: StateFlow<ViewEffects>
        get() = internalViewEffect

    private val events = Channel<WeatherViewEvent>(Channel.UNLIMITED)

    // TODO fetch data from location service
    private var userlocation = Geolocation(
        latitude = 47.60434116469604,
        longitude = -122.33595536470807
    )

    init {
        observerViewEvents()
    }

    private fun observerViewEvents() {
        events
            .consumeAsFlow()
            .onEach { event ->
                when (event) {
                    is WeatherViewEvent.Init -> handleInitEvent(event)
                }
            }
            .catch { }
            .launchIn(viewModelScope)
    }

    private fun updateViewState(weatherViewState: WeatherViewState) {
        interViewState.value = weatherViewState
    }

    fun handleEvent(event: WeatherViewEvent) {
        events.trySend(event)
    }

    private fun handleInitEvent(event: WeatherViewEvent.Init) {
        updateViewState(
            viewState.value.copy(
                processingViewState = ViewState.Show
            )
        )
        viewModelScope.launch {
            when (val weatherResult = getWeatherUseCase.run(location = userlocation)) {
                is Result.Success -> updateViewState(
                    viewState.value.copy(
                        weatherDetails = weatherResult.t
                    )
                )
                is Result.Failure -> {
                    logger.e(TAG, "Weather data error", weatherResult.t)
                }
            }
            updateViewState(
                viewState.value.copy(
                    processingViewState = ViewState.Hide
                )
            )
        }
    }

    companion object {
        val TAG = WeatherViewModel.javaClass.simpleName
    }
}

data class WeatherViewState(
    val weatherDetails: WeatherDetails? = null,
    val processingViewState: ViewState = ViewState.Hide
)

sealed class WeatherViewEvent {
    object Init : WeatherViewEvent()
}
