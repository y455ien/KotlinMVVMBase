package com.example.kotlinmvvmbase.widget.location_picker

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.kotlinmvvmbase.core.base.repository.Cache
import com.example.kotlinmvvmbase.databinding.FragmentLocationPickerBinding
import com.example.kotlinmvvmbase.util.extension.setNavigationResult
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.example.kotlinmvvmbase.R

class LocationPicker : Fragment() {
    private var _binding: FragmentLocationPickerBinding? = null
    private val binding get() = _binding!!
    private lateinit var locationProviderClient: FusedLocationProviderClient
    private lateinit var locationManager: LocationManager
    private lateinit var map: GoogleMap
    private var marker: Marker? = null
    private val geoCoder by lazy {
        Geocoder(activity, Cache.LANGUAGE.getCachedLocale())
    }

    private companion object {
        const val DEFAULT_ZOOM = 15
    }

    //---------Callbacks--------------------

    private val locationUpdatesCallback = object : LocationCallback() {
        override fun onLocationResult(locationResults: LocationResult) {
            updateMap(locationResults.lastLocation)
        }
    }

    private val onMapReadyCallback = OnMapReadyCallback { googleMap ->
        map = googleMap
        map.setOnMapClickListener(mapClickCallback)
    }

    private val mapClickCallback = GoogleMap.OnMapClickListener {
        map.clear()
        marker = map.addMarker(MarkerOptions().position(it).title("Workshop Location"))
        map.animateCamera(CameraUpdateFactory.newLatLng(it))
    }

    private var requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                if (!permissions.containsValue(false)) checkLocationService()
            }

    //---------Life cycle events--------------------

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLocationPickerBinding.inflate(inflater, container, false)
        initMembers()
        return (binding.root)
    }

    private fun initMembers() {
        locationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        binding.confirmLocationButton.setOnClickListener { this.setNavigationResult("Location", getLocationFromMarker()) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(onMapReadyCallback)
    }

    override fun onResume() {
        super.onResume()
        checkPermissions()
    }

    override fun onPause() {
        super.onPause()
        locationProviderClient.removeLocationUpdates(locationUpdatesCallback)
    }

    //---------Permission & Location handling--------------------

    private fun checkPermissions() {
        val permissionStatus = ContextCompat.checkSelfPermission(
                requireActivity().applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        when (permissionStatus) {
            PackageManager.PERMISSION_GRANTED -> checkLocationService()
            else -> requestPermissionLauncher.launch(
                    arrayOf(
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION
                    )
            )

        }
    }

    private fun checkLocationService() {
        var isLocationServiceEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (!isLocationServiceEnabled) {
//            BaseCommunicator.pushToastEvent("Location service is disabled")
        } else {
            getDeviceLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getDeviceLocation() {
        val locationResult = locationProviderClient.lastLocation
        locationResult.addOnCompleteListener { task ->
            when {
                task.isSuccessful -> handleCurrentLocation(task.result)
//                task.isCanceled -> BaseCommunicator.pushErrorEvent("We can't get your current location, please select location manually")
            }
        }
    }

    private fun handleCurrentLocation(location: Location?) {
        if (location != null) updateMap(location)
        else startLocationUpdates()
    }

    private fun getLocationFromMarker(): LocationModel? {
        return marker?.let { pickedPosition ->
            val addresses = geoCoder.getFromLocation(pickedPosition.position.latitude, pickedPosition.position.longitude, 1)
            val address = addresses.firstOrNull()
            return@let address?.let {
                LocationModel(lat = pickedPosition.position.latitude, lng = pickedPosition.position.longitude, address = it.getAddressLine(0))
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        val request = LocationRequest.create()
        request.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        request.interval = 0
        request.fastestInterval = 0
        request.numUpdates = 2
        locationProviderClient.requestLocationUpdates(
                request,
                locationUpdatesCallback,
                Looper.getMainLooper()
        )
    }

    private fun updateMap(result: Location) {
        map.clear()
        marker =
                map.addMarker(MarkerOptions().position(LatLng(result.latitude, result.longitude)))
        map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                        LatLng(
                                result.latitude,
                                result.longitude
                        ), DEFAULT_ZOOM.toFloat()
                )
        )
    }
}