package com.example.myapplication_day06

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication_day06.entities.Nature
import com.example.myapplication_day06.recyclerview.NatureAdapter

class MainActivity : AppCompatActivity() {

    private val natureAdapter =NatureAdapter()

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            run {
                if (result.resultCode == Activity.RESULT_OK) {
                    Log.e("name", "Anuj")
                    val intent = result.data
                    if (intent != null) {
                        if (intent.hasExtra(CreateActivity.EXTRA_TITLE_KEY) && intent.hasExtra(
                                CreateActivity.EXTRA_IMAGE
                            )
                        ) {
                            val title = intent.getStringExtra(CreateActivity.EXTRA_TITLE_KEY) as String
                            val image = intent.getIntExtra(CreateActivity.EXTRA_IMAGE,-1)
                            val nature = Nature(image, title)
                            natureAdapter.addData(nature)

                        }
                    }

                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = listOf(
            Nature(R.drawable.nature1, getString(R.string.nature1)),
            Nature(R.drawable.nature2, getString(R.string.nature2)),
            Nature(R.drawable.nature3, getString(R.string.nature3))
        )

        val recyclerView = findViewById<RecyclerView>(R.id.main_recycle)
        recyclerView.adapter = natureAdapter
        natureAdapter.changeData(data)
        recyclerView.setHasFixedSize(true)

        val createButton = findViewById<Button>(R.id.main_Create)

        createButton.setOnClickListener {
            val intent = Intent(this,CreateActivity::class.java)
            resultLauncher.launch(intent)

        }

    }
}