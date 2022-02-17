package com.example.application_convercy_converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.application_convercy_converter.db.AppDatabase
import com.example.application_convercy_converter.recycleView.FavroiteAdapter
import com.reza.roomapplication.db.entities.CurrencyBook

class MainActivityFavroite : AppCompatActivity() {
    private lateinit var dataBase: AppDatabase
    private var favroiteAdapter = FavroiteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_favroite)
        dataBase = AppDatabase.getDatabaseInstance(this)
        var customersDeatils = mutableListOf<CurrencyBook>()
        AppDatabase.databaseWriteExecutor.execute {
            customersDeatils = dataBase.CurrencyDAO().getAllUser()
            val recyclerView = findViewById<RecyclerView>(R.id.favroite_recycle)
            recyclerView.adapter = favroiteAdapter
            favroiteAdapter.changeData(customersDeatils)
            recyclerView.setHasFixedSize(true)
        }


    }
}