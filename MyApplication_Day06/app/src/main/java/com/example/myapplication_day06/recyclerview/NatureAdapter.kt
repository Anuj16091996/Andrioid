package com.example.myapplication_day06.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication_day06.R
import com.example.myapplication_day06.entities.Nature


class NatureAdapter() :
    RecyclerView.Adapter<NatureAdapter.NatureItemVieHolder>() {
    private val data = mutableListOf<Nature>()

    class NatureItemVieHolder(private val parent:NatureAdapter,private val containerView: View) :
        RecyclerView.ViewHolder(containerView) {
        var nature :Nature?=null
        val backgroundImage: ImageView = containerView.findViewById(R.id.list_item_image)
        val title: TextView = containerView.findViewById(R.id.list_item_title)
         val delete : Button =containerView.findViewById(R.id.list_item_delete_button)
        init {
                delete.setOnClickListener{
                    if(nature!=null){
                        parent.removeData(nature as Nature)
                    }
                }
        }
    }

    public fun changeData(dataSet: List<Nature>) {
        this.data.clear()
        this.data.addAll(dataSet)
        notifyDataSetChanged()
    }

    public fun addData(nature: Nature) {
        this.data.add(nature)
        notifyDataSetChanged()
    }

    private fun removeData(nature: Nature) {
        this.data.remove(nature)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NatureItemVieHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return NatureItemVieHolder(this,view)
    }

    override fun onBindViewHolder(holder: NatureItemVieHolder, position: Int) {
        val currentData = this.data[position]
        holder.title.setText(currentData.title)
        holder.nature=data[position]
        holder.backgroundImage.setImageResource(currentData.image)

    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0)
            return R.layout.item_list
        else R.layout.item_list_2
    }

    override fun getItemCount(): Int {
        return this.data.size
    }
}