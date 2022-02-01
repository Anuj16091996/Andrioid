package com.example.day02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivityCalculator : AppCompatActivity() {
    private lateinit var editText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_calculator)
        editText=findViewById<EditText>(R.id.display)
        val number0=findViewById<Button>(R.id.number_0)
        val number1=findViewById<Button>(R.id.number_1)
        val number2=findViewById<Button>(R.id.number_2)
        val number3=findViewById<Button>(R.id.number_3)
        val number4=findViewById<Button>(R.id.number_4)
        val number5=findViewById<Button>(R.id.number_5)
        val number6=findViewById<Button>(R.id.number_6)
        val number7=findViewById<Button>(R.id.number_7)
        val number8=findViewById<Button>(R.id.number_8)
        val number9=findViewById<Button>(R.id.number_9)
        val dot=findViewById<Button>(R.id.dot)
        val clear=findViewById<Button>(R.id.clear)

        number0.setOnClickListener(this::acceptedClick)
        number1.setOnClickListener(this::acceptedClick)
        number2.setOnClickListener(this::acceptedClick)
        number3.setOnClickListener(this::acceptedClick)
        number4.setOnClickListener(this::acceptedClick)
        number5.setOnClickListener(this::acceptedClick)
        number6.setOnClickListener(this::acceptedClick)
        number7.setOnClickListener(this::acceptedClick)
        number8.setOnClickListener(this::acceptedClick)
        number9.setOnClickListener(this::acceptedClick)
        dot.setOnClickListener(this::acceptedClick)
        clear.setOnClickListener(this::clearClick)
    }



    private fun acceptedClick(view: View) {
        if (view is Button) {
            val e: Button = view
            editText.append(e.text.toString())
        }
    }

    private fun clearClick(view: View){
        if(view is Button){
            val e:Button=view
            editText.setText("")
        }

    }
}