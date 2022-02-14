package com.example.myapplication_database.Database

import android.content.ContentValues
import android.content.Context
import com.example.myapplication_database.entities.Users

class UserTable(context: Context) {

    val dbHelper = DatingDbHelper(context)

    fun insertData(username: String, password: String, firstName: String, lastName: String) {
        //Map of column name + row value
        val values = ContentValues().apply {
            put(DatingDBContract.UserTable.USERNAME, username)
            put(DatingDBContract.UserTable.PASSWORD, password)
            put(DatingDBContract.UserTable.FIRST_NAME, firstName)
            put(DatingDBContract.UserTable.LAST_NAME, lastName)
        }

        val writeToDb = dbHelper.writableDatabase //EXPENSIVE if DB is closed
        //Second argument: What to do when there is no value.
        // Because of null: If there is no value then we just do not insert.
        val newRowId = writeToDb.insert(DatingDBContract.UserTable.TABLE_NAME, null, values)
    }

    fun getAll(): MutableList<Users> {
        val readFromDb = dbHelper.readableDatabase //EXPENSIVE if DB is closed.

        //Select Columns you want
        val projection = arrayOf(
            DatingDBContract.UserTable.USER_ID,
            DatingDBContract.UserTable.USERNAME,
            DatingDBContract.UserTable.PASSWORD,
            DatingDBContract.UserTable.FIRST_NAME,
            DatingDBContract.UserTable.LAST_NAME,
        )

        //WHERE PART only to avoid SQL Injection
        val selection =
            "${DatingDBContract.UserTable.USERNAME} = ? AND ${DatingDBContract.UserTable.PASSWORD} = ?"
        val selectionArgs = arrayOf("rezaUser", "rezaPassword")

        //Sorting
        val orderBy = "${DatingDBContract.UserTable.FIRST_NAME} DESC"

        val cursor = readFromDb.query(
            DatingDBContract.UserTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            orderBy
        )

        //cursor starts at -1
//        while (cursor.moveToNext()) {
//            Log.e(
//                "Reading DB",
//                cursor.getString(cursor.getColumnIndexOrThrow(DatingDBContract.UserTable.USERNAME))
//            )
//        }

        val userList = mutableListOf<Users>()
        with(cursor) {
            while (moveToNext()) {//Moves from -1 row to next one
                val users = Users(
                    getInt(getColumnIndexOrThrow(DatingDBContract.UserTable.USER_ID)),
                    getString(getColumnIndexOrThrow(DatingDBContract.UserTable.USERNAME)),
                    getString(getColumnIndexOrThrow(DatingDBContract.UserTable.PASSWORD)),
                    getString(getColumnIndexOrThrow(DatingDBContract.UserTable.FIRST_NAME)),
                    getString(getColumnIndexOrThrow(DatingDBContract.UserTable.LAST_NAME)),
                )
                userList.add(users)
            }
        }

        cursor.close()

        return userList

    }

    fun get(username: String): Users? {
        val readFromDb = dbHelper.readableDatabase //EXPENSIVE if DB is closed.

        //Select Columns you want
        val projection = arrayOf(
            DatingDBContract.UserTable.USER_ID,
            DatingDBContract.UserTable.USERNAME,
            DatingDBContract.UserTable.PASSWORD,
            DatingDBContract.UserTable.FIRST_NAME,
            DatingDBContract.UserTable.LAST_NAME,
        )
        val cursor = readFromDb.rawQuery(
            "Select * from ${DatingDBContract.UserTable.TABLE_NAME}" +
                    " where  ${DatingDBContract.UserTable.USERNAME} like '$username' ", null
        )

        with(cursor) {
            if (cursor.moveToNext()) {
                return Users(
                    getInt(getColumnIndexOrThrow(DatingDBContract.UserTable.USER_ID)),
                    getString(getColumnIndexOrThrow(DatingDBContract.UserTable.USERNAME)),
                    getString(getColumnIndexOrThrow(DatingDBContract.UserTable.PASSWORD)),
                    getString(getColumnIndexOrThrow(DatingDBContract.UserTable.FIRST_NAME)),
                    getString(getColumnIndexOrThrow(DatingDBContract.UserTable.LAST_NAME)),
                )
            } else return null
        }

    }


    fun checkUserNameAnPassword(username: String, password: String): Boolean {
        for (user in getAll()) {
            if (user.userName == username) {
                if (user.passWord == password) {
                    return true
                }
            } else return false
        }
        return false
    }

    fun update(user: Users, newPassword: String): Boolean {
        val dbWrite = dbHelper.writableDatabase
        user.passWord = newPassword
        val values = ContentValues().apply {
            put(DatingDBContract.UserTable.USERNAME, user.userName)
            put(DatingDBContract.UserTable.PASSWORD, user.passWord)
            put(DatingDBContract.UserTable.FIRST_NAME, user.firstName)
            put(DatingDBContract.UserTable.LAST_NAME, user.lastName)
        }

        val selection = "${DatingDBContract.UserTable.USER_ID}=? "
        val selectionArgs = arrayOf(user.ID.toString())
        val rowsUpadted = dbWrite.update(
            DatingDBContract.UserTable.TABLE_NAME,
            values,
            selection,
            selectionArgs
        )

        if (rowsUpadted >= 1) {
            return true
        }
        return false
    }

    fun delete(user: Users): Boolean {
        val dbWrite = dbHelper.writableDatabase
        val selection = "${DatingDBContract.UserTable.USER_ID }=? "
        val selectionArgs = arrayOf(user.ID.toString())
        val delete = dbWrite.delete(
            DatingDBContract.UserTable.TABLE_NAME,
            selection,
            selectionArgs
        )
        if (delete > 0) return true

        return false
    }


}