package com.example.myapplication_database.Database

import android.provider.BaseColumns

object DatingDBContract {
    //Tables
    object UserTable : BaseColumns {
        const val TABLE_NAME = "user"
        const val USER_ID = "user_id"
        const val USERNAME = "username"
        const val PASSWORD = "password"
        const val FIRST_NAME = "first_name"
        const val LAST_NAME = "last_name"
    }
    object MessageTable : BaseColumns {
        const val MESSAGE_ID = "message_id"
        const val RECEIVER_ID = "receiver_id"
        const val SENDER_ID = "sender_id"
        const val DATE = "date"
        const val CONTENT = "content"
    }
}