package com.example.myapplication_pratical_exam

import android.app.Activity
import android.app.TaskStackBuilder
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private lateinit var createToast: Button
    private lateinit var createSnack: Button
    private lateinit var displayToast: EditText
    private lateinit var displaySnack: EditText
    private lateinit var toastList: ArrayList<Int>
    private lateinit var snakcList: ArrayList<Int>

    companion object {
        const val EXTRA_SERIALIZABLE_KEY_TOAST = "main_name"
        const val EXTRA_SERIALIZABLE_KEY_SNACK = "main_name2"
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            run {
                if (result.resultCode == Activity.RESULT_OK) {

                    val intent = result.data
                    if (intent != null) {
                        if (intent.hasExtra(MainActivity_Toast.APPEND_DATA)) {
                            val tempData: ArrayList<Int> =
                                intent.getStringArrayListExtra(MainActivity_Toast.APPEND_DATA) as ArrayList<Int>
                            if (tempData != null) {
                                toastList.addAll(tempData)
                                Log.e("anuj", toastList.sum().toString())
                                Log.e("anuj", tempData.toString())
                                displayToast.setText(toastList.sum().toString())
                            }

                        }

                        if (intent.hasExtra(MainActivity_Snack.APPEND_DATA_SNACK)) {
                            val tempData: ArrayList<Int> =
                                intent.getStringArrayListExtra(MainActivity_Snack.APPEND_DATA_SNACK) as ArrayList<Int>
                            if (tempData != null) {
                                snakcList.addAll(tempData)
                                Log.e("anuj", snakcList.sum().toString())
                                Log.e("anuj", tempData.toString())
                                displaySnack.setText(snakcList.sum().toString())
                            }

                        }
                    }

                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createToast = findViewById<Button>(R.id.main_toast)
        createSnack = findViewById<Button>(R.id.main_snack)
        displayToast = findViewById<EditText>(R.id.main_toastdisplay)
        displaySnack = findViewById<EditText>(R.id.main_snackdisplay)
        toastList = ArrayList()
        snakcList = ArrayList()
        createToast.setOnClickListener(this::sendToast)
        createSnack.setOnClickListener(this::sendSnack)
        displayToast.setText("0")
        displaySnack.setText("0")


    }


    private fun sendToast(view: View) {
        val intent = Intent(this, MainActivity_Toast::class.java)
        intent.putExtra(EXTRA_SERIALIZABLE_KEY_TOAST, toastList)
        resultLauncher.launch(intent)
    }

    private fun sendSnack(view: View) {
        val intent = Intent(this, MainActivity_Snack::class.java)
        intent.putExtra(EXTRA_SERIALIZABLE_KEY_SNACK, snakcList)
        resultLauncher.launch(intent)
    }

}