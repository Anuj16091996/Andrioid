package com.example.myapplication_room_database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import com.reza.roomapplication.db.AppDatabase
import com.reza.roomapplication.db.entities.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val hello = findViewById<TextView>(R.id.main_hello)
        val changeButton = findViewById<Button>(R.id.main_changeValue)
        val database = AppDatabase.getDatabaseInstance(this)
        val userDao = database.userDao()
        var user: User? = null

        AppDatabase.databaseWriteExecutor.execute {
             user = User("rohits", "a", "rohit", "Amazing")
                val id=userDao.insert(user as User)
                user?.id=id

        }


//        AppDatabase.databaseWriteExecutor.execute {
//            //On a different Thread. So we might not have access to everythign.
//           val users = userDao.getUser("rohits")
//            hello.text = user?.lastName
//        }
        userDao.getUserLive("rohit").observe(this, Observer {user
            hello.text = user?.firstName
        })

        changeButton.setOnClickListener {
            user?.lastName="Radno"
            AppDatabase.databaseWriteExecutor.execute{
                userDao.delete(user as User)
            }
        }

    }
}