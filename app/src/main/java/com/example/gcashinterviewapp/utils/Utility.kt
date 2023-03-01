package com.example.gcashinterviewapp.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat



private fun isLocationPermissionGranted(ctx : Context): Boolean {
    return false
    /*return if (ActivityCompat.checkSelfPermission(
            ctx,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            ctx,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            ctx,
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            requestcode
        )
        false
    } else {
        true
    }*/
}