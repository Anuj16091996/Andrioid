package com.example.mainactivity_send_data_back_and_forth

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var login: Button
    lateinit var register: Button
    private var userdata: Userdata? = null

    companion object {
        const val EXTRA_SERIALIZABLE_KEY = "main_name"
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            run {
                if (result.resultCode == Activity.RESULT_OK) {
                    Log.e("name", "Anuj")
                    val intent = result.data
                    if (intent != null) {
                        if (intent.hasExtra(MainActivityRegister.REGISTER_KEY)) {
                            val tempData =
                                intent.getSerializableExtra(MainActivityRegister.REGISTER_KEY) as Userdata
                            userdata = tempData

                        }
                    }

                }
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = findViewById<EditText>(R.id.login_username)
        password = findViewById<EditText>(R.id.login_password)
        login = findViewById<Button>(R.id.login_login)
        register = findViewById<Button>(R.id.login_register)
        login.setOnClickListener(this::loginUser)
        register.setOnClickListener(this::registerUser)
    }


    private fun loginUser(view: View) {
        if (username.text.toString() == "" || password.text.toString() == "") {
            Toast.makeText(this, "Username and password cannot be left Blank", Toast.LENGTH_SHORT)
                .show()
        } else {
            if (userdata == null) {
                Toast.makeText(this, "Register First", Toast.LENGTH_SHORT).show()
            } else {
                if (username.text.toString() != userdata!!.username || password.text.toString() != userdata!!.password) {
                    Toast.makeText(this, "Invalid Login id and password", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(this, MainActivityLoggedin::class.java)
                    intent.putExtra(EXTRA_SERIALIZABLE_KEY, userdata)
                    resultLauncher.launch(intent)
                }
            }

        }

    }

    private fun registerUser(view: View) {
        val intent = Intent(this, MainActivityRegister::class.java)
        resultLauncher.launch(intent)
    }
}