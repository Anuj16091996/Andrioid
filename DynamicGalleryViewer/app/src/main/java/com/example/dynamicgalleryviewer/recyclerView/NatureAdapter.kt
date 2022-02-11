package com.example.dynamicgalleryviewer.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.dynamicgalleryviewer.R
import com.example.dynamicgalleryviewer.entities.Nature
import java.util.zip.Inflater

class NatureAdapter() :
    RecyclerView.Adapter<NatureAdapter.NatureItemVieHolder>() {
    private val dataset = mutableListOf<Nature>()

    // view holder to display dynamic content
    class NatureItemVieHolder(private val parent: NatureAdapter, private val containerView: View) :
        RecyclerView.ViewHolder(containerView) {
        var nature :Nature?=null
        val backgroundImage: ImageView = containerView.findViewById(R.id.list_item_image)
        val title: TextView = containerView.findViewById(R.id.list_item_title)
        private val delete: Button = containerView.findViewById(R.id.list_item_delete_button)

        init {
            delete.setOnClickListener {
                if(nature!=null){
                    parent.removeData(nature as Nature)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NatureItemVieHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return NatureItemVieHolder(this, view)
    }

    fun addData(nature: Nature) {
        this.dataset.add(nature)
        notifyDataSetChanged()
    }

    fun changeData(data: MutableList<Nature>) {
        this.dataset.clear()
        this.dataset.addAll(data)
        notifyDataSetChanged()
    }
     fun removeData(nature: Nature) {
        this.dataset.remove(nature)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NatureItemVieHolder, position: Int) {
        val currentData = dataset[position]
        holder.title.setText(currentData.stringID)
        holder.nature=dataset[position]
        holder.backgroundImage.setImageResource(currentData.imageID)
    }

    override fun getItemCount(): Int = dataset.count()
    override fun getItemViewType(position: Int): Int {
        return R.layout.item_list
    }

}