package com.example.application_convercy_converter.entities

import com.google.gson.annotations.SerializedName
import java.util.*

data class ResultofUSDollar(
    @SerializedName("conversion_rates") val conversion_rates:Map<String, Double>
) {

}