package com.example.myapplication_discorg_album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication_discorg_album.entities.Album
import com.example.myapplication_discorg_album.recycleView.DiscorgsAdapter
import com.squareup.picasso.Picasso

class MainActivityRecivingActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var title: TextView
    private lateinit var label: TextView
    private lateinit var addButton: Button
    private var data: Album? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_reciving)
        imageView = findViewById(R.id.receiveMain_image)
        title = findViewById(R.id.receiveMain_title)
        label = findViewById(R.id.receiveMain_label)
        addButton = findViewById(R.id.receiveMain_button)

        val intent = intent
        if (intent.hasExtra(DiscorgsAdapter.DiscorgsViewHolder.EXTRA_INTENT)) {
            data =
                intent.getSerializableExtra(DiscorgsAdapter.DiscorgsViewHolder.EXTRA_INTENT) as Album
        }

        if (data != null) {
            Picasso.get()
                .load(data?.image)
                .into(imageView)

            title.setText(data?.title)
            label.setText(data?.label.toString())
        }
    }
}