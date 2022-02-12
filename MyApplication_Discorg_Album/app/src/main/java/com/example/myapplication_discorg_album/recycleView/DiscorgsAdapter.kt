package com.example.myapplication_discorg_album.recycleView

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication_discorg_album.MainActivity
import com.example.myapplication_discorg_album.MainActivityRecivingActivity
import com.example.myapplication_discorg_album.R
import com.example.myapplication_discorg_album.entities.Album
import com.squareup.picasso.Picasso

class DiscorgsAdapter() : RecyclerView.Adapter<DiscorgsAdapter.DiscorgsViewHolder>() {
    private val dataset = mutableListOf<Album>()

    class DiscorgsViewHolder(private val parent: DiscorgsAdapter, private val containerView: View) :
        RecyclerView.ViewHolder(containerView) {

        companion object{
            const val EXTRA_INTENT="valie"
        }


        var position: Album? = null
        val click: CardView = containerView.findViewById(R.id.item_click)
        val imageView: ImageView = containerView.findViewById(R.id.itemList_Image)
        val title: TextView = containerView.findViewById(R.id.itemList_Title)
        val genre: TextView = containerView.findViewById(R.id.itemList_genre)
        val context: Context = title.context

        init {
            click.setOnClickListener {
                val intent = Intent(context, MainActivityRecivingActivity::class.java)
                intent.putExtra(EXTRA_INTENT,position)
                context.startActivity(intent)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscorgsViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return DiscorgsViewHolder(this, view)
    }

    override fun onBindViewHolder(holder: DiscorgsViewHolder, position: Int) {
        val currentData = dataset[position]
        holder.title.setText(currentData.title)
        holder.genre.append(currentData.genre.toString())
        holder.position = dataset[position]

        if (currentData.Image_Link != null && currentData.Image_Link == "") {
            holder.imageView.setImageResource(R.drawable.data)
        } else {
            Picasso.get()
                .load(currentData.Image_Link)
                .into(holder.imageView)
        }

    }

    fun addData(nature: Album) {
        this.dataset.add(nature)
        notifyDataSetChanged()
    }

    fun changeData(data: MutableList<Album>) {
        this.dataset.clear()
        this.dataset.addAll(data)
        notifyDataSetChanged()
    }

    fun removeData(nature: Album) {
        this.dataset.remove(nature)
        notifyDataSetChanged()
    }

    fun removeAllData() {
        this.dataset.clear()
        notifyDataSetChanged()
    }


    override fun getItemCount() = dataset.count()
    override fun getItemViewType(position: Int): Int {
        return R.layout.item_list
    }

}