package com.example.application_convercy_converter.Network

import com.example.application_convercy_converter.entities.ResultofUSDollar
import com.example.myapplication_discorg_album.Network.RestCountriesInterface
import com.example.myapplication_discorg_album.entities.ResultOfRestCountries
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.*
import kotlin.collections.ArrayList

private const val BASE_URL = "https://v6.exchangerate-api.com/v6/"
private val retrofits =
    Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL).build()

interface UsDollarInterFace {
    @GET("28aef8e2e94745c1f2720fbf/latest/USD/")
    fun getSearchResults(
    ): Call<ResultofUSDollar>
}

object UsDollarAPI {
    val RETROFIT_SERVICE: UsDollarInterFace by lazy {
        retrofits.create(UsDollarInterFace::class.java)
    }
}