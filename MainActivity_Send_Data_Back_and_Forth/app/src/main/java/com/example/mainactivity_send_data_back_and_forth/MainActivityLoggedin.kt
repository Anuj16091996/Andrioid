package com.example.mainactivity_send_data_back_and_forth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class MainActivityLoggedin : AppCompatActivity() {
    private lateinit var name:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_loggedin)
        name=findViewById<TextView>(R.id.loggedIn_hello)

        val intent=intent
        if(intent.hasExtra(MainActivity.EXTRA_SERIALIZABLE_KEY)){
            val user : Userdata?= intent.getSerializableExtra(MainActivity.EXTRA_SERIALIZABLE_KEY) as? Userdata
            val data= String.format("Hello "+user?.firstname +" "+ user?.lastname)
            name.setText(data)
        }

    }
}