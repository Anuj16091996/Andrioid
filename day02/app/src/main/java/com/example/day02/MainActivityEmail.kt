package com.example.day02

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class MainActivityEmail : AppCompatActivity() {
    lateinit var emailTo:EditText
    lateinit var subject:EditText
    lateinit var body:EditText
    lateinit var arrayList: ArrayList<String>
    companion object
    {
        const val key="key"
    }
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        run {
            Log.e("Dsadsa", "Adsadsa")
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                if (intent != null) {
                    if (intent.hasExtra(MainActivityEmailRecivie.return_data)) {
                        emailTo.setText( intent.getStringExtra(MainActivityEmailRecivie.return_data).toString())

                    }
                }
            }
        }
    }

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
        arrayList = ArrayList<String>()
        arrayList.add(emailTo.text.toString());
        arrayList.add(subject.text.toString())
        arrayList.add(body.text.toString())
        val intent=Intent(this,MainActivityEmailRecivie::class.java)
        intent.putExtra(key,arrayList);
        resultLauncher.launch(intent)
    }
}