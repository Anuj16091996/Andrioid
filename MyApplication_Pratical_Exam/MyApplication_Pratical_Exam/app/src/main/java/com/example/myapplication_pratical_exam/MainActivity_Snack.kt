package com.example.myapplication_pratical_exam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity_Snack : AppCompatActivity() {
    private lateinit var displayText: EditText
    private lateinit var short: Button
    private lateinit var long: Button
    private lateinit var create: Button
    private var clickLong = 1
    private lateinit var reciveList: ArrayList<Int>

    companion object {
        const val APPEND_DATA_SNACK = "values"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_snack)

        displayText = findViewById<EditText>(R.id.toast_Snackdisplay)
        short = findViewById<Button>(R.id.Snack_short)
        long = findViewById<Button>(R.id.Snack_long)
        create = findViewById<Button>(R.id.Snack_createSnack)
        create.setOnClickListener(this::displaySnack)
        long.setOnClickListener { clickLong = 2 }
        reciveList = ArrayList()

        val intent = intent
        if (intent.hasExtra(MainActivity.EXTRA_SERIALIZABLE_KEY_SNACK)) {
            val numberList: ArrayList<Int>? =
                intent.getIntArrayExtra(MainActivity.EXTRA_SERIALIZABLE_KEY_SNACK) as? ArrayList<Int>
            if (numberList != null) {
                reciveList = numberList
            }
        }
    }

    private fun displaySnack(view: View) {
        if (clickLong == 1) {
            var value = 1
            Snackbar.make(
                findViewById(R.id.Snack_long),
                displayText.text.toString(),
                Snackbar.LENGTH_SHORT
            ).setAction("Dismiss", View.OnClickListener { value = 2 }).show()
            if (value == 1)
                reciveList.add(1)
        } else {
            var value = 1
            Snackbar.make(
                findViewById(R.id.Snack_long),
                displayText.text.toString(),
                Snackbar.LENGTH_LONG
            ).setAction("Dismiss",
                View.OnClickListener { value = 2 }).show()
            if (value == 1)
                reciveList.add(1)

        }
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(MainActivity_Snack.APPEND_DATA_SNACK, reciveList)
        setResult(RESULT_OK, intent)
        Log.e("value", reciveList.toString())
    }
}