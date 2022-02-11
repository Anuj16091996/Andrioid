package com.example.myapplication_day07.entities

import com.google.gson.annotations.SerializedName

data class Data(
    var login: String,
    @SerializedName("avatar_url") var avatar: String,
    @SerializedName("url") var link :String
) {

}