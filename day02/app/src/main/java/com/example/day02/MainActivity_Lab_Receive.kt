package com.example.day02

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity_Lab_Receive : AppCompatActivity() {
    private lateinit var receiveText: EditText
    private lateinit var receiveNumber1: EditText
    private lateinit var receiveNumber2: EditText
    private lateinit var receiveNumber3: EditText
    private lateinit var receiveNumber4: EditText
    private lateinit var receiveNumber5: EditText
    private lateinit var receiveNumber6: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_lab_receive)
        receiveText = findViewById<EditText>(R.id.labReceive_Text)
        receiveNumber1 = findViewById<EditText>(R.id.labReceive_number1)
        receiveNumber2 = findViewById<EditText>(R.id.labReceive_number2)
        receiveNumber3 = findViewById<EditText>(R.id.labReceive_number3)
        receiveNumber4 = findViewById<EditText>(R.id.labReceive_number4)
        receiveNumber5 = findViewById<EditText>(R.id.labReceive_number5)
        receiveNumber6 = findViewById<EditText>(R.id.labReceive_number6)
        val receivebutton=findViewById<Button>(R.id.labReceive_Reset)
        receivebutton.setOnClickListener(this::resetArray)

        val intent = intent
        if (intent.hasExtra(MainActivity_Lab_dice.LAB_KEY)) {
            val numberList = intent.getStringArrayListExtra(MainActivity_Lab_dice.LAB_KEY)
            if (numberList != null) {
                val numberCount = String.format("Total Dice roll -: " + numberList.count())
                receiveText.setText(numberCount)
                val occurrences1: Int = Collections.frequency(numberList, 1)
                val numCount1 = String.format("Number of times 1 roll  is - $occurrences1")
                 receiveNumber1.setText(numCount1)

                val occurrences2: Int = Collections.frequency(numberList, 2)
                val numCount2 = String.format("Number of times 2 roll  is - $occurrences2")
                receiveNumber2.setText(numCount2)

                val occurrences3: Int = Collections.frequency(numberList, 3)
                val numCount3 = String.format("Number of times 3 roll  is - $occurrences3")
                receiveNumber3.setText(numCount3)

                val occurrences4: Int = Collections.frequency(numberList, 4)
                val numCount4 = String.format("Number of times 4 roll  is - $occurrences4")
                receiveNumber4.setText(numCount4)

                val occurrences5: Int = Collections.frequency(numberList, 5)
                val numCount5 = String.format("Number of times 5 roll  is - $occurrences5")
                receiveNumber5.setText(numCount5)

                val occurrences6: Int = Collections.frequency(numberList, 6)
                val numCount6 = String.format("Number of times 6 roll  is - $occurrences6")
                receiveNumber6.setText(numCount6)
            }

        }
    }

    private fun resetArray(view:View){


        val intent= Intent(this,MainActivity_Lab_dice::class.java)
        setResult(RESULT_OK, intent)
        startActivity(intent)

    }

}



