package com.example.day04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity_Menu_Context : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu_context)

        val popbutton = findViewById<Button>(R.id.menu_pop)

        popbutton.setOnClickListener {
            Toast.makeText(this, "Pop clicked", Toast.LENGTH_SHORT).show()
        }
        registerForContextMenu(popbutton)

        popbutton.setOnLongClickListener{
            Toast.makeText(this, "Pop Long Clicked", Toast.LENGTH_SHORT).show()
            //True will consume the next event and false will let execution of next event
            true
        }
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu,menu)
    }
}