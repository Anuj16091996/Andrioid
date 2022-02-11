package com.example.myapplication_day06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CreateActivity : AppCompatActivity() {
    companion object  {
        const val EXTRA_TITLE_KEY="name"
        const val EXTRA_IMAGE="data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val title = findViewById<EditText>(R.id.create_edit)
        val create = findViewById<Button>(R.id.create_button)
        val images = listOf(
            R.drawable.nature1,
            R.drawable.nature2,
            R.drawable.nature3
        )
        create.setOnClickListener{
            val intent=intent.apply {
                putExtra(EXTRA_TITLE_KEY,title.text.toString())
                putExtra(EXTRA_IMAGE,images.random())
            }
            setResult(RESULT_OK,intent)
            finish()
        }

    }
}