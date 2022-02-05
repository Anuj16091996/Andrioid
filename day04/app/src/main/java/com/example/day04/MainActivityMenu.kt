package com.example.day04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivityMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.m1, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_m1 -> {
                Toast.makeText(this, "M1 Cicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menum1_first,R.id.menum1_second->{
                Toast.makeText(this, "submenu Cicked", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.menu_m2 -> {
                Snackbar.make(findViewById(R.id.menu_m2),"M2 Clicked",Snackbar.LENGTH_SHORT).setAction("Dismiss",
                    View.OnClickListener {  }).show()
                true
            }

            R.id.menu_m3 -> {
                Toast.makeText(this, "M3 Cicked", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.menu_m4 -> {
                Toast.makeText(this, "M4 Cicked", Toast.LENGTH_SHORT).show()
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }


    }
}