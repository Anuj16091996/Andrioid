package com.example.day04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class MainActivityImageGallery : AppCompatActivity() {
    private lateinit var imageDisplay: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_image_gallery)

        imageDisplay = findViewById<ImageView>(R.id.Gallery_setimage)
        val imagefirst = findViewById<ImageView>(R.id.Gallery_dice3)
        val imageSecond = findViewById<ImageView>(R.id.Gallery_dice4)
        val imageThird = findViewById<ImageView>(R.id.Gallery_dice5)

        imagefirst.setOnClickListener (this::setImage)

        imageSecond.setOnClickListener (this::setImage)

        imageThird.setOnClickListener (this::setImage)
    }

    private fun setImage(view: View) {
        if (view is ImageView)
            imageDisplay.setImageDrawable(view.drawable)
    }


}
