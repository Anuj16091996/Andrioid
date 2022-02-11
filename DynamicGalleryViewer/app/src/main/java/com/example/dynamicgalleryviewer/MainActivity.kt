package com.example.dynamicgalleryviewer

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicgalleryviewer.entities.Nature
import com.example.dynamicgalleryviewer.recyclerView.NatureAdapter

class MainActivity : AppCompatActivity() {

    private val natureAdapter =NatureAdapter()

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            run {
                if (result.resultCode == Activity.RESULT_OK) {
                    Log.e("name", "Anuj")
                    val intent = result.data
                    if (intent != null) {
                        if (intent.hasExtra(MainActivityCreateActivity.EXTRA_TITLE_KEY) && intent.hasExtra(
                                MainActivityCreateActivity.EXTRA_IMAGE
                            )
                        ) {
                            val title = intent.getStringExtra(MainActivityCreateActivity.EXTRA_TITLE_KEY) as String
                            val image = intent.getIntExtra(MainActivityCreateActivity.EXTRA_IMAGE,-1)
                            val nature = Nature(image, title)
                            natureAdapter.addData(nature)

                        }
                    }

                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Created a list of images in list
        val dataofImages = mutableListOf<Nature>()
        dataofImages.add(Nature(R.drawable.nature1, getString(R.string.nature1)))
        dataofImages.add(Nature(R.drawable.nature2, getString(R.string.nature2)))
        dataofImages.add(Nature(R.drawable.nature3, getString(R.string.nature3)))


        //To access recycle view in activity main
        val recyclerView = findViewById<RecyclerView>(R.id.main_recycle)
        recyclerView.adapter = natureAdapter
        natureAdapter.changeData(dataofImages)
        recyclerView.setHasFixedSize(true)//if reccycler view height and width doesnt change


        val createButton = findViewById<Button>(R.id.main_Create)

        createButton.setOnClickListener {
            val intent = Intent(this,MainActivityCreateActivity::class.java)
            resultLauncher.launch(intent)

        }
    }
}