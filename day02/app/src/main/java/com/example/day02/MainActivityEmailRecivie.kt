package com.example.day02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.core.widget.addTextChangedListener

class MainActivityEmailRecivie : AppCompatActivity(),TextWatcher {
    private lateinit var receiveTo: EditText
    private lateinit var receiveSubject: EditText
    private lateinit var receiveBody: EditText
    private val intentToMoveBack:Intent = Intent()
    companion object{
       const val return_data="name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_email_recivie)
        receiveTo = findViewById<EditText>(R.id.reciveto)
        receiveSubject = findViewById<EditText>(R.id.recivesubject)
        receiveBody = findViewById<EditText>(R.id.recivebody)
        val intent = intent


        if (intent.hasExtra(MainActivityEmail.key)) {
            val array = intent.getStringArrayListExtra(MainActivityEmail.key)
            if (array != null) {
                receiveTo.setText(array[0])
                receiveSubject.setText(array[1])
                receiveBody.setText(array[2])

            }
        }

       receiveTo.addTextChangedListener(this)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(p0: Editable?) {

       intentToMoveBack.putExtra(return_data,p0.toString())
        setResult(RESULT_OK,intentToMoveBack)
    }

}