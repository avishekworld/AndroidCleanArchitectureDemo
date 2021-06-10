package com.example.cleanarchitecturedemo.views.navigation

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.cleanarchitecturedemo.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow

class NavigationViewModel(private val context: Context) : ViewModel() {

    private val _internalState = MutableLiveData(InternalState(viewState = getDefaultViewState()))
    val viewState : LiveData<ViewState> = _internalState.map {
        it.viewState
    }
    private val state : InternalState
        get() = requireNotNull(_internalState.value)

    private val channel = Channel<Event>(Channel.UNLIMITED)

    init {
        channel
            .receiveAsFlow()
            .onEach {
                Log.d(TAG, "event $it")
                when(it) {
                    is Event.MapInit -> handleMapInit(it)
                    is Event.MapPointClicked -> handleMapPointClicked(it)
                    is Event.MarkerAdded -> handleMarkerAdded(it)
                    is Event.PolylineAdded -> handlePolylineAdded(it)
                }
            }
            .catch {
                Log.d(TAG, "event channel error")
            }
            .launchIn(viewModelScope)
    }

    fun handleEvent(event : Event) {
        channel.offer(event)
    }

    private fun updateState(internalState: InternalState) {
        _internalState.value = internalState
    }

    private fun getDefaultViewState() : ViewState {
        return ViewState(
            NavigationMapState.Init,
            MarkerState.Init,
            PolylineState.Init)
    }

    private fun handleMapInit(mapInit: Event.MapInit) {
        updateState(state.copy(
            viewState = state.viewState.copy(
                mapState = NavigationMapState.Show(
                    zoomGesturesEnabled = true,
                    zoomControlsEnabled = true,
                    zoomLevel = MAP_ZOOM_LEVEL,
                    centerLocation = CENTER_LOCATION,
                    mapClickListener = {
                            mapLatLongClicked -> handleEvent(Event.MapPointClicked(mapLatLongClicked))
                        }
                    )
            )
        ))
    }

    private fun handleMapPointClicked(mapPointClicked: Event.MapPointClicked) {
        when(state.mapPointSelectedList.size) {
            0 -> {
                createMarkerOption(mapPointClicked.latLong, context.getString(R.string.source)).also { markerOptions ->
                    updateState(state.copy(
                        mapPointSelectedList = state.mapPointSelectedList.toMutableList().also {
                            it.add(mapPointClicked.latLong)
                        },
                        viewState = state.viewState.copy(
                            markerState = MarkerState.Show(markerOptions)
                        )))
                }
            }
            1 -> {
                createMarkerOption(mapPointClicked.latLong, context.getString(R.string.destination)).also { markerOptions ->
                    createPolylineOptions(state.mapPointSelectedList[0], mapPointClicked.latLong).also { polylineOptions ->
                        updateState(state.copy(
                            mapPointSelectedList = state.mapPointSelectedList.toMutableList().also {
                                it.add(mapPointClicked.latLong)
                            },
                            viewState = state.viewState.copy(
                                markerState = MarkerState.Show(markerOptions),
                            )))
                        updateState(state.copy(
                            viewState = state.viewState.copy(
                                polylineState = PolylineState.Show(polylineOptions)
                            )))
                    }
                }
            }
            else -> {
                createMarkerOption(mapPointClicked.latLong, context.getString(R.string.destination)).also { markerOptions ->
                    updateState(state.copy(
                        mapPointSelectedList = emptyList(),
                        viewState = state.viewState.copy(
                            polylineState = PolylineState.Hide(requireNotNull(state.polyline))
                        )))
                    state.mapMarkerList.forEach { marker ->
                        updateState(state.copy(
                            viewState = state.viewState.copy(
                                markerState = MarkerState.Hide(marker),
                                polylineState = PolylineState.Init
                            )))
                    }
                    updateState(state.copy(mapMarkerList = emptyList()))
                    updateState(state.copy(
                        mapPointSelectedList = state.mapPointSelectedList.toMutableList().also {
                            it.add(mapPointClicked.latLong)
                        },
                        viewState = state.viewState.copy(
                            markerState = MarkerState.Show(markerOptions)
                        )))
                }
            }
        }
    }

    private fun handleMarkerAdded(markerAdded: Event.MarkerAdded) {
        updateState(state.copy(
            mapMarkerList = state.mapMarkerList.toMutableList().also {
                it.add(markerAdded.marker)
            },
            viewState = state.viewState.copy(
                markerState = MarkerState.Init,
                polylineState = PolylineState.Init
            )
        ))
    }

    private fun handlePolylineAdded(polylineAdded: Event.PolylineAdded) {
        updateState(state.copy(
            polyline = polylineAdded.polyline,
            viewState = state.viewState.copy(
                polylineState = PolylineState.Init
            )
        ))
    }


    private fun createMarkerOption(point : LatLng, title : String) : MarkerOptions {
        return MarkerOptions()
            .position(point)
            .title(title)
    }

    private fun createPolylineOptions(point1 : LatLng, point2 : LatLng) : PolylineOptions {
        return PolylineOptions()
            .add(point1)
            .add(point2)
            .width(POLY_LINE_WIDTH)
            .color(R.color.colorPrimaryDark)
    }

    sealed class Event {
        object MapInit : Event()
        data class MapPointClicked(val latLong : LatLng) : Event()
        data class MarkerAdded(val marker: Marker) : Event()
        data class PolylineAdded(val polyline: Polyline) : Event()
    }

    private data class InternalState(val mapPointSelectedList : List<LatLng> = emptyList(),
                                     val mapMarkerList : List <Marker> = emptyList(),
                                     val polyline : Polyline? = null,
                                     val viewState: ViewState
    )

    data class ViewState(val mapState : NavigationMapState,
                         val markerState: MarkerState,
                         val polylineState: PolylineState)

    sealed class NavigationMapState {
        object Init : NavigationMapState()
        object Hide : NavigationMapState()
        data class Show(val zoomGesturesEnabled : Boolean,
                        val zoomControlsEnabled : Boolean,
                        val zoomLevel : Float,
                        val centerLocation : LatLng,
                        val mapClickListener : GoogleMap.OnMapClickListener) : NavigationMapState()
    }
    sealed class MarkerState {
        object Init : MarkerState()
        data class Hide(val marker : Marker) : MarkerState()
        data class Show(val markerOptions : MarkerOptions) : MarkerState()
    }

    sealed class PolylineState {
        object Init : PolylineState()
        data class Hide(val polyline: Polyline) : PolylineState()
        data class Show(val polylineOptions : PolylineOptions) : PolylineState()
    }

    companion object {
        const val TAG = "NavigationViewModel"
        const val POLY_LINE_WIDTH = 22f
        const val MAP_ZOOM_LEVEL = 17f
        val CENTER_LOCATION = LatLng(47.6062, -122.3321)
    }
}