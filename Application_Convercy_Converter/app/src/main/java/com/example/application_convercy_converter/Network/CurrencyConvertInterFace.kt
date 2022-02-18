package com.example.application_convercy_converter.Network

import com.example.application_convercy_converter.entities.AmeraDonConversion
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://v6.exchangerate-api.com/v6/28aef8e2e94745c1f2720fbf/pair/"
private val retrofit =
    Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL).build()


interface CurrencyConvertInterFace {

    @GET("{from}/{to}/{amount}")
    fun getSearchResults(
        @Path("from") from: String,
        @Path("to") to: String,
        @Path("amount") amount: Double?,
    ): Call<AmeraDonConversion>
}

object ConvertAPI {
    val RETROFIT_SERVICE: CurrencyConvertInterFace by lazy {
        retrofit.create(CurrencyConvertInterFace::class.java)
    }
}