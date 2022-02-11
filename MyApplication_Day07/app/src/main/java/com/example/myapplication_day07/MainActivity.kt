package com.example.myapplication_day07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import com.example.myapplication_day07.entities.Data
import com.example.myapplication_day07.network.GithubApi
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), retrofit2.Callback<Data> {
    lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.main_avatar)
        val callUser = GithubApi.retrofitService.getUser("MadReza")
        callUser.enqueue(this)
    }
//        val callFollowrs = GithubApi.retrofitService.getFollowers("MadReza")
//
//
//
//        callFollowrs.enqueue(object : Callback<List<Data>> {
//            override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
//                val listoffo = response.body()
//                if (listoffo != null) {
//                    for (data in listoffo) {
//                        //Log.e("anuj",data.avatar.toString())
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })
//    }

    override fun onResponse(call: Call<Data>, response: Response<Data>) {
        val user = response.body()
        if (user != null) {
            Log.e("anuj", user.avatar.toString())
            Picasso.get()
                .load("https://avatars.githubusercontent.com/u/8241914?v=4")
                .into(imageView)

        }
    }

    override fun onFailure(call: Call<Data>, t: Throwable) {
        TODO("Not yet implemented")
    }
}
