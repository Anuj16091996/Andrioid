package com.example.myapplication_day07.network

import com.example.myapplication_day07.entities.Data
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://api.github.com/"
private val retrofit =
    Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()


interface GitHubApiService {



    @GET("users/{user}/followers")
    fun getFollowers(@Path("user") user: String ):Call<List<Data>>

    @GET("users/{user}")//Anottaion value and path value shoudl be same
    fun getUser(@Path("user") user: String): Call<Data>
}


object GithubApi {
    val retrofitService: GitHubApiService by lazy {
        retrofit.create(GitHubApiService::class.java)

    }
}