package com.example.unsplashimageapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * Main Activity sets the content view [activity_main], a fragment container that contains the [PriorityFragment]
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}