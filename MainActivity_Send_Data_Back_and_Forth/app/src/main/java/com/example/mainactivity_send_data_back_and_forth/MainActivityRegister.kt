package com.example.mainactivity_send_data_back_and_forth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivityRegister : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var passowrd: EditText
    private lateinit var repeatpassword: EditText
    private lateinit var firstname: EditText
    private lateinit var lastname: EditText

    companion object{
       const val REGISTER_KEY="name_re"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_register)

        username = findViewById<EditText>(R.id.register_username)
        passowrd = findViewById<EditText>(R.id.register_password)
        repeatpassword = findViewById<EditText>(R.id.register_repeatPassword)
        firstname = findViewById<EditText>(R.id.register_firstName)
        lastname = findViewById<EditText>(R.id.register_lastName)
        val submit = findViewById<Button>(R.id.register_submit)

        submit.setOnClickListener(this::sendDataBack)
    }

    private fun sendDataBack(view: View) {
        if (username.text.toString() == "" || passowrd.text.toString() == "" || repeatpassword.text.toString() == "" || firstname.text.toString() == "" || lastname.text.toString() == "") {
            Toast.makeText(this, "Data can not be blank", Toast.LENGTH_SHORT).show()
        } else if (passowrd.text.toString() != repeatpassword.text.toString()) {
            Toast.makeText(this, "Password and repeat password should be same", Toast.LENGTH_SHORT)
                .show()
        } else {
            val userDetails = Userdata(
                username.text.toString(),
                passowrd.text.toString(),
                repeatpassword.text.toString(),
                firstname.text.toString(),
                lastname.text.toString()
            )

            val intent= Intent()
            intent.putExtra(REGISTER_KEY, userDetails)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

}