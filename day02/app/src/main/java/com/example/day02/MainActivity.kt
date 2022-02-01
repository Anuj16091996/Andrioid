package com.example.day02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var name :EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name =findViewById<EditText>(R.id.main_getValue)
        name.setText("Anuj")

        val button=findViewById<Button>(R.id.main_submitID)
        val button2=findViewById<Button>(R.id.main_submitID_Other)
        button.setOnClickListener{
            Log.w("Inside Class","Click")
        }

        button2.setOnClickListener(this::acceptedClick)
    }

    private fun acceptedClick(view: View){
        if(view is Button){
            val e:Button=view
             Log.w("Button click",e.text.toString())
        }

    }



}