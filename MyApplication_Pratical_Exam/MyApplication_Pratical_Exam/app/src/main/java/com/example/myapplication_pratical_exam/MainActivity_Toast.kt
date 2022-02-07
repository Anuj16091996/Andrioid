package com.example.myapplication_pratical_exam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity_Toast : AppCompatActivity() {
    private lateinit var displayText: EditText
    private lateinit var short: Button
    private lateinit var long: Button
    private lateinit var create: Button
    private var clickLong = 1
    private lateinit var reciveList: ArrayList<Int>
    companion object{
       const val APPEND_DATA="value"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_toast)

        displayText = findViewById<EditText>(R.id.toast_toastdisplay)
        short = findViewById<Button>(R.id.toast_short)
        long = findViewById<Button>(R.id.toast_long)
        create = findViewById<Button>(R.id.toast_createToast)
        create.setOnClickListener(this::displayToast)
        long.setOnClickListener { clickLong = 2 }
        reciveList= ArrayList()

        val intent = intent
        if (intent.hasExtra(MainActivity.EXTRA_SERIALIZABLE_KEY_TOAST)) {
            val numberList:ArrayList<Int>? = intent.getIntArrayExtra(MainActivity.EXTRA_SERIALIZABLE_KEY_TOAST) as? ArrayList<Int>
            if (numberList != null) {
                reciveList= numberList
            }
        }

    }

    private fun displayToast(view: View) {
        if (clickLong == 1)
            Toast.makeText(this, displayText.text.toString(), Toast.LENGTH_SHORT).show()
        else {
            Toast.makeText(this, displayText.text.toString(), Toast.LENGTH_LONG).show()
        }
        reciveList.add(1)
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(APPEND_DATA,reciveList)
        setResult(RESULT_OK, intent)
        Log.e("value",reciveList.toString())
    }

}