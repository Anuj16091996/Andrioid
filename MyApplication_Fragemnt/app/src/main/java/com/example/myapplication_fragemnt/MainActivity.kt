package com.example.myapplication_fragemnt

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    private lateinit var displayButton: Button
    private var isFragmentDisplayed = false;
    private lateinit var simpleFragment: SimpleFragment
    private lateinit var fragmentManager: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayButton = findViewById<Button>(R.id.main_OpenandCLose)
        simpleFragment = SimpleFragment().newInstance()
        displayButton.setOnClickListener(this::showFragement)
        fragmentManager = supportFragmentManager
    }


    private fun showFragement(view: View) {
        if (!isFragmentDisplayed) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.fragment_container, simpleFragment).addToBackStack(null)
                .commit()
            displayButton.setText("Close")
            isFragmentDisplayed = true
        } else {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(simpleFragment).commit()
            displayButton.setText("Open")
            isFragmentDisplayed = false
        }
    }
}