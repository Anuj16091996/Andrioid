package com.example.myapplication_discorg_album.entities
import java.io.Serializable
import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("title") val title:String?,
    @SerializedName("genre") val genre:List<String>?,
    @SerializedName("label") val label:List<String>?,
    @SerializedName("thumb") val Image_Link:String,
    @SerializedName("cover_image")val image:String

):Serializable  {
}