package com.example.myapplication_pratical_exam

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity_Snack : AppCompatActivity() {
    private lateinit var displayText: EditText

    private lateinit var long: Switch
    private lateinit var create: Button
    private lateinit var reciveList: ArrayList<Int>

    companion object {
        const val APPEND_DATA_SNACK = "values"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_snack)

        displayText = findViewById<EditText>(R.id.toast_Snackdisplay)

        long = findViewById<Switch>(R.id.Snack_long)
        create = findViewById<Button>(R.id.Snack_createSnack)
        create.setOnClickListener(this::displaySnack)
        long.setOnCheckedChangeListener  { _, isChecked ->
            if (isChecked)
                long.text="Short"

            else
                long.text="Long"
        }

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

    @SuppressLint("ResourceType")
    private fun displaySnack(view: View) {
        if (long.isChecked) {
            val snack = Snackbar.make(
                findViewById(R.id.snack_layout),
                displayText.text.toString(),
                Snackbar.LENGTH_SHORT
            )
            snack.setAction("Dismiss short") { reciveList.removeFirst() }
            snack.show()
            reciveList.add(1)
        } else {

            Snackbar.make(
                findViewById(R.id.snack_layout),
                displayText.text.toString(),
                Snackbar.LENGTH_LONG
            ).setAction("Dismiss") { reciveList.removeFirst() }.show()
            reciveList.add(1)
        }
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(MainActivity_Snack.APPEND_DATA_SNACK, reciveList)
        setResult(RESULT_OK, intent)
        Log.e("value", reciveList.toString())
    }
}