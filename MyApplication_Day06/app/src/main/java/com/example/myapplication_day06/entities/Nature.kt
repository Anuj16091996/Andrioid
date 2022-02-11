package com.example.myapplication_day06.entities

import android.content.Context
import androidx.annotation.DisplayContext
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Nature(
    @DrawableRes val image: Int,
     val title: String
) {

    constructor(context: Context ,@DrawableRes image: Int, @StringRes title: Int)
            :this(image,context.resources.getString(title)){

    }

}