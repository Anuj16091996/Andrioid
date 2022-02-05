package com.example.mainactivity_send_data_back_and_forth

import java.io.Serializable

data class Userdata(
    var username: String,
    val password: String,
    val repeatPassword: String,
    val firstname: String,
    val lastname: String
) :Serializable {

}