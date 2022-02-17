package com.example.application_convercy_converter.recycleView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.application_convercy_converter.R
import com.example.application_convercy_converter.entities.CountryDetails
import com.reza.roomapplication.db.entities.CurrencyBook
import com.squareup.picasso.Picasso

class FavroiteAdapter() : RecyclerView.Adapter<FavroiteAdapter.FavroiteViewHolder>() {
    private val dataset = mutableListOf<CurrencyBook>()

    class FavroiteViewHolder(
        private val parent: FavroiteAdapter,
        private val containerView: View
    ) :
        RecyclerView.ViewHolder(containerView) {
        var position: CurrencyBook? = null
        val click: CardView = containerView.findViewById(R.id.favroite_click)
        val fromcurrencyCode: TextView =
            containerView.findViewById(R.id.Favroitre_FromCountryCurrency)
        val fromcurrencyValue: TextView =
            containerView.findViewById(R.id.Favroitre_FromCountryCurrency)
        val fromCountryName: TextView = containerView.findViewById(R.id.Favroitre_FromCountryName)
        val fromCountryImage: ImageView = containerView.findViewById(R.id.favroite_FromImage)
        val tourrencyCode: TextView = containerView.findViewById(R.id.Favroitre_ToCountryCurrency)
        val tocurrencyValue: TextView = containerView.findViewById(R.id.Favroitre_ToCountryCurrency)
        val toCountryName: TextView = containerView.findViewById(R.id.Favroitre_ToCountryName)
        val toCountryImage: ImageView = containerView.findViewById(R.id.favroite_ToImage)


        init {
            click.setOnClickListener {
                Log.e("anuj", "in Afap")
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavroiteViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return FavroiteAdapter.FavroiteViewHolder(this, view)
    }

    fun changeData(data: MutableList<CurrencyBook>) {
        this.dataset.clear()
        this.dataset.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: FavroiteViewHolder, position: Int) {
        val currentData = dataset[position]
        holder.fromcurrencyCode.setText(currentData.FromCurrency)
        holder.fromcurrencyValue.setText(currentData.FromCurrency)
        holder.fromCountryName.setText(currentData.FromCountry)
        Picasso.get().load(currentData.FromImage).into(holder.fromCountryImage)
        holder.tourrencyCode.setText(currentData.ToCurrency)
        holder.tocurrencyValue.setText(currentData.ToCurrency)
        holder.toCountryName.setText(currentData.ToCountry)
        Picasso.get().load(currentData.ToImage).into(holder.toCountryImage)
        holder.position = dataset[position]
    }

    override fun getItemCount() = dataset.count()
    override fun getItemViewType(position: Int): Int {
        return R.layout.favroite_custom
    }
}