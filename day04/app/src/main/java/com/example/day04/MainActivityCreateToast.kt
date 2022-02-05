package com.example.day04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivityCreateToast : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_create_toast)

        val button=findViewById<Button>(R.id.createToast_Button)
        val text=findViewById<EditText>(R.id.createToast_EditText)
        button.setOnClickListener {

            if(text.text.toString()!="")
            Toast.makeText(this,text.text.toString(),Toast.LENGTH_SHORT).show()

            else
                Toast.makeText(this,"Enter Some data",Toast.LENGTH_SHORT).show()
        }
    }
}