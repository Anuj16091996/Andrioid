package com.example.final_pratical_exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.final_pratical_exam.db.AppDatabase
import com.example.final_pratical_exam.recycleView.CheckoutAdapter

class CheckoutActivity : AppCompatActivity() {
    private lateinit var dataBase: AppDatabase
    private lateinit var purchaseButton: Button
    private lateinit var recyclerView: RecyclerView
    var checkoutAdapter = CheckoutAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)
        dataBase = AppDatabase.getDatabaseInstance(this)
        recyclerView = findViewById<RecyclerView>(R.id.checkout_recycle)
        AppDatabase.databaseWriteExecutor.execute {
            val detailsOfMenu = dataBase.MenuDAO().getAllMenu()
            recyclerView.adapter = checkoutAdapter
            checkoutAdapter.InsertAllData(detailsOfMenu)
        }

        purchaseButton = findViewById(R.id.checkout_purchase)
        purchaseButton.setOnClickListener(this::checkOutConfirmation)
    }


    private fun checkOutConfirmation(view: View) {

    }
}