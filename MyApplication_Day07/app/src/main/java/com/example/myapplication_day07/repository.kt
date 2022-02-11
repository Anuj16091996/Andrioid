package com.example.myapplication_day07

import com.example.myapplication_day07.entities.Data
import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("login") val name:String?,
    val owner :Data
) {
}