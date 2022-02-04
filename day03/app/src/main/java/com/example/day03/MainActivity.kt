package com.example.day03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text=findViewById<TextView>(R.id.textView)
        val stringvalue=resources.getString(R.string.hello,"Anuj")
        text.text=stringvalue
        Log.e("main",stringvalue)


    }


}