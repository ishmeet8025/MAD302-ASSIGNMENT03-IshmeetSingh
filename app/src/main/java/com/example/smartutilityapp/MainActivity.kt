package com.example.smartutilityapp

/**
 * Course: MAD302
 * Assignment: 3
 * Name: Ishmeet Singh
 * Student ID: A00202436
 * Description: Smart Utility App with async data, permissions, and navigation
 */

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.location.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etInput = findViewById<EditText>(R.id.etInput)
        val btnFetch = findViewById<Button>(R.id.btnFetch)
        val btnLocation = findViewById<Button>(R.id.btnLocation)
        val btnNext = findViewById<Button>(R.id.btnNext)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        
        // Async Data Fetch
        btnFetch.setOnClickListener {

            val input = etInput.text.toString()

            // Security: validation
            if (input.isBlank()) {
                tvResult.text = "Please enter valid input"
                return@setOnClickListener
            }

            tvResult.text = "Loading..."

            lifecycleScope.launch {
                try {
                    val result = fetchData()
                    tvResult.text = "$result\nHello $input"
                } catch (e: Exception) {
                    tvResult.text = "Error fetching data"
                }
            }
        }

        // Location Feature
        btnLocation.setOnClickListener {
            getLocation(tvResult)
        }

        // Navigation
        btnNext.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("message", tvResult.text.toString())
            startActivity(intent)
        }
    }

    // Simulated API Call
    private suspend fun fetchData(): String {
        delay(2000)
        return "Data fetched successfully from server"
    }

    // LOCATION HANDLING
    private fun getLocation(tvResult: TextView) {

        // Permission check
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                100
            )
            return
        }

        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            2000
        ).build()

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {

                val location = result.lastLocation

                if (location != null) {
                    tvResult.text = "Lat: ${location.latitude}, Lng: ${location.longitude}"
                } else {
                    tvResult.text = "Getting location... please wait"
                }

                fusedLocationClient.removeLocationUpdates(this)
            }
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            mainLooper
        )
    }
}