package com.example.day01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)
    }

    override fun onPause() {
        super.onPause()
        Log.w("Execute on pause","Pause work")
    }

    override fun onResume() {
        super.onResume()
        Log.w("Execute on resume","Resume work")
    }

    override fun onStart() {
        super.onStart()
        Log.w("Execute on Start","Start work")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w("Execute on destroy","app destroy")
    }

}