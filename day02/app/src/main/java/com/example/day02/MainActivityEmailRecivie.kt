package com.example.day02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class MainActivityEmailRecivie : AppCompatActivity() {
    private lateinit var receiveTo: EditText
    lateinit var receiveSubject: EditText
    private lateinit var receiveBody: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_email_recivie)
        receiveTo = findViewById<EditText>(R.id.reciveto)
        receiveSubject = findViewById<EditText>(R.id.recivesubject)
        receiveBody = findViewById<EditText>(R.id.recivebody)
        val intent = intent

        if (intent.hasExtra("data")) {
            val array = intent.getStringArrayListExtra("data")
            if (array != null) {
                receiveTo.setText(array[0])
                receiveSubject.setText(array[1])
                receiveBody.setText(array[2])

            }
        }
    }
}