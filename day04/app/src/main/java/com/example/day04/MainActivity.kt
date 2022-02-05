package com.example.day04

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val testButton = findViewById<Button>(R.id.main_toast)
        testButton.setOnClickListener {
            val myToast = Toast.makeText(this, "Not implemented info", Toast.LENGTH_LONG)
            myToast.show()
        }

        val t1 = Teacher("Anuj", "Narang")
        Log.w("main", t1.firstname)

        val sendButton = findViewById<Button>(R.id.main_send)
        sendButton.setOnClickListener {
            val send = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Hello")
                type = "text/plain"
            }
            try {
                startActivity(send)
            } catch (e: ActivityNotFoundException) {

            }

        }
    }
}