package com.example.day02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivityEmail : AppCompatActivity() {
    lateinit var emailTo:EditText
    lateinit var subject:EditText
    lateinit var body:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_email)

        emailTo=findViewById<EditText>(R.id.to)
        subject=findViewById<EditText>(R.id.subject)
        body=findViewById<EditText>(R.id.body)
        val send=findViewById<Button>(R.id.send)
        send.setOnClickListener(this::dataSent)

    }


    private fun dataSent(view:View){
        val arrayList = ArrayList<String>()
        arrayList.add(emailTo.text.toString());
        arrayList.add(subject.text.toString())
        arrayList.add(body.text.toString())
        val intent=Intent(this,MainActivityEmailRecivie::class.java)
        intent.putExtra("data",arrayList);
        startActivity(intent)
    }
}