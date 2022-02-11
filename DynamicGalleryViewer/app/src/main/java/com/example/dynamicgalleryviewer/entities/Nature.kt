package com.example.dynamicgalleryviewer.entities

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Nature(@DrawableRes val imageID: Int, val stringID: String) {

    constructor(context: Context, @DrawableRes imageID: Int, @StringRes string: Int) : this(
        imageID,
        context.resources.getString(string)
    )
}