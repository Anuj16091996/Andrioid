package com.example.day02

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity_Lab_dice : AppCompatActivity() {
    private lateinit var imageDisplay: ImageView
    private lateinit var rollDice: Button
    private var numberList= ArrayList<Int>()
    companion object{
        const val LAB_KEY="name"
    }
    private lateinit var showDetails:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_lab_dice)
        imageDisplay = findViewById<ImageView>(R.id.labDice_number)
        rollDice = findViewById<Button>(R.id.labDice_Roll)
        showDetails=findViewById<Button>(R.id.labDice_History)
        rollDice.setOnClickListener(this::rollDice)
        showDetails.setOnClickListener(this::showDetails)
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        run {
            if (result.resultCode == Activity.RESULT_OK) {
               numberList.clear()
            }
        }
    }


    private fun showDetails(view: View){
        val intent= Intent(this,MainActivity_Lab_Receive::class.java)
        intent.putExtra(LAB_KEY,numberList)
        resultLauncher.launch(intent)
    }

    private fun rollDice(view: View) {
        val random = (1..6).random()
        numberList.add(random)
        when (random) {
            1 -> {
                imageDisplay.setImageResource(R.drawable.dice1)
            }
            2->{
                imageDisplay.setImageResource(R.drawable.dice2)
            }
            3->{
                imageDisplay.setImageResource(R.drawable.dice3)
            }
            4->{
                imageDisplay.setImageResource(R.drawable.dice4)
            }
            5->{
                imageDisplay.setImageResource(R.drawable.dice5)
            }
            6->{
                imageDisplay.setImageResource(R.drawable.dice6)
            }
        }
    }
}


