package com.example.myapplication_dialog_box

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var username: EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById<Button>(R.id.main_alert)
        button.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this).apply {
                setMessage(R.string.setMessage)
                setTitle(R.string.setDialog)
            }
            builder.setPositiveButton(R.string.positiveButton, this::positiveClick)
            // builder.setNegativeButton(R.string.negativeButton){_:DialogInterface,_:Int->Toast.makeText(this,"Neagtive",Toast.LENGTH_SHORT).show()}
            //builder.setNeutralButton(R.string.neutralButton){_:DialogInterface,_:Int->Toast.makeText(this,"Neutral",Toast.LENGTH_SHORT).show()}
            builder.setNeutralButton(R.string.neutralButton, this::positiveClick)
            builder.setNegativeButton(R.string.negativeButton, this::positiveClick)
            builder.show()
        }

        val arraybutton = findViewById<Button>(R.id.main_arrayalert)
        arraybutton.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this).apply {
                setTitle(R.string.setDialog)
            }
            builder.setItems(R.array.arraylist, this::arrayClick)
            builder.show()
        }

        val custombutton = findViewById<Button>(R.id.main_custom)
        custombutton.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val dialog: View = layoutInflater.inflate(R.layout.custom_layout, null)
            builder.setView(dialog)
            builder.setPositiveButton("Login", this::negativeClick)
            builder.setNegativeButton("Cancel", this::negativeClick)
            username = dialog.findViewById<EditText>(R.id.username)
            password = dialog.findViewById<EditText>(R.id.password)
            builder.show()
        }


    }

    private fun negativeClick(dialog: DialogInterface, which: Int) {
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> {
                Toast.makeText(this, username.text.toString(), Toast.LENGTH_SHORT).show()
            }
            else-> {
                Toast.makeText(this, password.text.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun positiveClick(dialog: DialogInterface, which: Int) {
        when (which) {
            DialogInterface.BUTTON_NEGATIVE -> {
                Toast.makeText(this, "Neagtive", Toast.LENGTH_SHORT).show()
            }
            DialogInterface.BUTTON_NEUTRAL -> {
                Toast.makeText(this, "Neutral", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, "Positive", Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun arrayClick(dialog: DialogInterface, which: Int) {
        when (which) {
            0 -> {
                Toast.makeText(this, "Neutral", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, "Positive", Toast.LENGTH_SHORT).show()
            }
        }
    }
}