package com.example.cleanarchitecturedemo.views.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cleanarchitecturedemo.R
import com.example.cleanarchitecturedemo.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private val viewModel: NavigationViewModel by viewModel()
    lateinit var navigationMap: GoogleMap
    lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (supportFragmentManager.findFragmentById(R.id.content_layout) as SupportMapFragment)?.let {
            it.getMapAsync(this)
        }
        viewModel.viewState.observe(this) {
            renderMap(it.mapState)
            renderMapMarker(it.markerState)
            renderPolyLine(it.polylineState)
        }
    }

    override fun onMapReady(map: GoogleMap) {
        navigationMap = map
        viewModel.handleEvent(NavigationViewModel.Event.MapInit)
    }

    private fun renderMap(mapState: NavigationViewModel.NavigationMapState) {
        when (mapState) {
            is NavigationViewModel.NavigationMapState.Show -> {
                navigationMap.uiSettings.isZoomGesturesEnabled = mapState.zoomGesturesEnabled
                navigationMap.uiSettings.isZoomControlsEnabled = mapState.zoomControlsEnabled
                navigationMap.moveCamera(CameraUpdateFactory.zoomTo(mapState.zoomLevel))
                navigationMap.moveCamera(CameraUpdateFactory.newLatLng(mapState.centerLocation))
                navigationMap.setOnMapClickListener(mapState.mapClickListener)
            }
            is NavigationViewModel.NavigationMapState.Hide -> {}
            is NavigationViewModel.NavigationMapState.Init -> {}
        }
    }

    private fun renderMapMarker(mapMarkerState: NavigationViewModel.MarkerState) {
        when (mapMarkerState) {
            is NavigationViewModel.MarkerState.Show -> {
                val marker = navigationMap.addMarker(mapMarkerState.markerOptions)
                viewModel.handleEvent(NavigationViewModel.Event.MarkerAdded(marker))
            }
            is NavigationViewModel.MarkerState.Hide -> {
                mapMarkerState.marker.remove()
            }
            else -> {}
        }
    }

    private fun renderPolyLine(polylineState: NavigationViewModel.PolylineState) {
        when (polylineState) {
            is NavigationViewModel.PolylineState.Show -> {
                val polyline = navigationMap.addPolyline(polylineState.polylineOptions)
                viewModel.handleEvent(NavigationViewModel.Event.PolylineAdded(polyline))
            }
            is NavigationViewModel.PolylineState.Hide -> {
                polylineState.polyline.remove()
            }
            else -> {}
        }
    }
}
