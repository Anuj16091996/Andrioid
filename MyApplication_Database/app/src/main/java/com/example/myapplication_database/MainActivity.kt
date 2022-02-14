package com.example.myapplication_database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication_database.Database.UserTable

class MainActivity : AppCompatActivity() {
    private lateinit var userTable: UserTable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userTable = UserTable(this)

        userTable.insertData("Anuj161996", "password", "Anuj", "Narang")
        val myUsers = userTable.getAll()
        val Anuj = userTable.get("Anuj161996")
        val Reza=userTable.get("rezaUser")

        if (Reza != null) {
            userTable.delete(Reza)
        }

        if (Anuj != null) {
            userTable.update(Anuj,"NewPassword")
        }


    }

}