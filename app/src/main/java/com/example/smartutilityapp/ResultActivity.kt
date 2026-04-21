package com.example.smartutilityapp

/**
 * Displays final result screen
 */

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvFinal = findViewById<TextView>(R.id.tvFinal)

        // Null safety
        val message = intent.getStringExtra("message") ?: "No data received"

        tvFinal.text = message
    }
}