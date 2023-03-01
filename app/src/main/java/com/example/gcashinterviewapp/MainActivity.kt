package com.example.gcashinterviewapp

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.viewpager.widget.ViewPager
import com.example.gcashinterviewapp.databinding.ActivityMainBinding
import com.example.gcashinterviewapp.ui.main.CurrentWeatherFragment
import com.example.gcashinterviewapp.ui.main.SectionsPagerAdapter
import com.example.gcashinterviewapp.utils.PermissionsUtils
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private val REQUEST_CHECK_SETTINGS: Int = 101
    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34
    private lateinit var sectionsPagerAdapter : SectionsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        binding.executePendingBindings()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        checkAndCallLocation()
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()
        //checkAndCallLocation()
    }

private fun checkAndCallLocation(){
    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        if (PermissionsUtils.isLocationEnabled(this)) {
            callLastLocation()
        } else {
            createLocationRequest()
        }
    } else {
        requestForegroundPermissions()
    }
}


fun createLocationRequest() {
    val locationRequest = com.google.android.gms.location.LocationRequest.create().apply {
        interval = 10000
        fastestInterval = 5000
        priority = com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
    }
    val builder = LocationSettingsRequest.Builder()
        .addLocationRequest(locationRequest)

    val client: SettingsClient = LocationServices.getSettingsClient(this)
    val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

    task.addOnSuccessListener { locationSettingsResponse ->
        // All location settings are satisfied. The client can initialize
        // location requests here.
        // ...
        //callLastLocation()
        checkAndCallLocation()
    }

    task.addOnFailureListener { exception ->
        if (exception is ResolvableApiException) {
            // Location settings are not satisfied, but this can be fixed
            // by showing the user a dialog.
            try {
                // Show the dialog by calling startResolutionForResult(),
                // and check the result in onActivityResult().
                exception.startResolutionForResult(
                    this@MainActivity,
                    REQUEST_CHECK_SETTINGS
                )
            } catch (sendEx: IntentSender.SendIntentException) {
                // Ignore the error.
                Log.d("Error", sendEx.toString())
            }
        }
    }
}

override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    val states = LocationSettingsStates.fromIntent(intent)
    when (requestCode) {
        REQUEST_CHECK_SETTINGS ->
            when (resultCode) {
                Activity.RESULT_OK ->checkAndCallLocation()
            }
    }
}


@SuppressLint("MissingPermission")
private fun callLastLocation() {
    fusedLocationClient.lastLocation
        .addOnSuccessListener { location: Location? ->
            Log.d(
                "location",
                location?.longitude.toString() + " " + location?.latitude.toString()
            )

            (sectionsPagerAdapter.getItem(0) as CurrentWeatherFragment)
                .callWeatherApi(location?.latitude,location?.longitude,getString(R.string.api_key))
        }
}

private fun foregroundPermissionApproved(): Boolean {
    return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
        this,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )
}

private fun requestForegroundPermissions() {
    val provideRationale = foregroundPermissionApproved()

    // If the user denied a previous request, but didn't check "Don't ask again", provide
    // additional rationale.
    if (provideRationale) {
        Snackbar.make(
            binding.root,
            R.string.permission_rationale,
            Snackbar.LENGTH_LONG
        )
            .setAction(R.string.ok) {
                // Request permission
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
                )
            }
            .show()
    } else {
        //Log.d(TAG, "Request foreground only permission")
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
        )
    }
}

override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<String>,
    grantResults: IntArray
) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    //Log.d(TAG, "onRequestPermissionResult")

    when (requestCode) {
        REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE -> when {
            grantResults.isEmpty() ->
                // If user interaction was interrupted, the permission request
                // is cancelled and you receive empty arrays.
                //Log.d(TAG, "User interaction was cancelled.")
                Log.d("Main", "User interaction was cancelled.")

            grantResults[0] == PackageManager.PERMISSION_GRANTED -> checkAndCallLocation()
                //callLastLocation()


            else -> {
                // Permission denied.
                //updateButtonState(false)

                Snackbar.make(
                    binding.root,
                    R.string.permission_denied_explanation,
                    Snackbar.LENGTH_LONG
                )
                    .setAction(R.string.settings) {
                        // Build intent that displays the App settings screen.
                        val intent = Intent()
                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        val uri = Uri.fromParts(
                            "package",
                            BuildConfig.APPLICATION_ID,
                            null
                        )
                        intent.data = uri
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }
                    .show()
            }
        }
    }
}

}