package com.reza.sqlliteapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

/**
 * If you want to access database
    val dbHelper = DatingDbHelper(context)
 */

private const val SQL_CREATE_TABLE =
    "CREATE TABLE ${DatingDBContract.UserTable.TABLE_NAME} (" + 
            "${DatingDBContract.UserTable.USER_ID} INTEGER PRIMARY KEY, " + //"${BaseColumns._ID}"
            "${DatingDBContract.UserTable.USERNAME} TEXT, " +
            "${DatingDBContract.UserTable.PASSWORD} TEXT, " +
            "${DatingDBContract.UserTable.FIRST_NAME} TEXT, " +
            "${DatingDBContract.UserTable.LAST_NAME} TEXT" +
            ")"

private const val DROP_TABLE = "DROP TABLE IF EXISTS ${DatingDBContract.UserTable.TABLE_NAME}"

class DatingDbHelper(context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

        companion object {
            const val DATABASE_NAME = "dating.db"
            const val DATABASE_VERSION = 1
        }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}


















