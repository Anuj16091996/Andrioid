package com.reza.sqlliteapp.db

import android.content.ContentValues
import android.content.Context
import com.reza.sqlliteapp.entities.User

class UserTable(context: Context) {
    private val dbHelper = DatingDbHelper(context)

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

    fun authenticate(username: String, password: String) : Boolean {
        for (user in getAll()) {
            if (user.username == username) {
                return user.getPassword() == password
            }
        }
        return false;
    }

    fun get(username: String): User? {
        val readFromDb = dbHelper.readableDatabase //EXPENSIVE if DB is closed.

        //Select Columns you want
        val projection = arrayOf(
            DatingDBContract.UserTable.USER_ID,
            DatingDBContract.UserTable.USERNAME,
            DatingDBContract.UserTable.PASSWORD,
            DatingDBContract.UserTable.FIRST_NAME,
            DatingDBContract.UserTable.LAST_NAME,
        )

        val cursor = readFromDb.rawQuery("SELECT * from ${DatingDBContract.UserTable.TABLE_NAME} " +
                "where ${DatingDBContract.UserTable.USERNAME} like '$username%'", null)

        with(cursor) {
            if (moveToNext()){
                val user = User(
                    getInt(getColumnIndexOrThrow(DatingDBContract.UserTable.USER_ID)),
                    getString(getColumnIndexOrThrow(DatingDBContract.UserTable.USERNAME)),
                    getString(getColumnIndexOrThrow(DatingDBContract.UserTable.PASSWORD)),
                    getString(getColumnIndexOrThrow(DatingDBContract.UserTable.FIRST_NAME)),
                    getString(getColumnIndexOrThrow(DatingDBContract.UserTable.LAST_NAME)),
                )
                return user
            }
            else return null
        }
    }

    fun getAll(): List<User> {
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
        //val selection = "${DatingDBContract.UserTable.USERNAME} = ? AND ${DatingDBContract.UserTable.PASSWORD} = ?"
        //val selectionArgs = arrayOf("rezaUser", "rezaPassword")


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

        val userList = mutableListOf<User>()

        with(cursor) {
            while (moveToNext()) {//Moves from -1 row to next one
                val user = User(
                    getInt(getColumnIndexOrThrow(DatingDBContract.UserTable.USER_ID)),
                    getString(getColumnIndexOrThrow(DatingDBContract.UserTable.USERNAME)),
                    getString(getColumnIndexOrThrow(DatingDBContract.UserTable.PASSWORD)),
                    getString(getColumnIndexOrThrow(DatingDBContract.UserTable.FIRST_NAME)),
                    getString(getColumnIndexOrThrow(DatingDBContract.UserTable.LAST_NAME)),
                )
                userList.add(user)
            }
        }
        cursor.close()
        return userList
    }

    fun update(user: User): Boolean {
        val dbWrite = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(DatingDBContract.UserTable.PASSWORD, user.getPassword())
            put(DatingDBContract.UserTable.FIRST_NAME, user.firstName)
            put(DatingDBContract.UserTable.LAST_NAME, user.lastName)
        }

        val whereClaus = "${DatingDBContract.UserTable.USER_ID} = ?"
        val whereClausArgs = arrayOf(user.id.toString())

        val rowsUpdated = dbWrite.update(
            DatingDBContract.UserTable.TABLE_NAME,
            values,
            whereClaus,
            whereClausArgs
        )

        if (rowsUpdated == 1)
            return true
        return false
    }

    fun delete(user: User) : Boolean {

        val dbWrite = dbHelper.writableDatabase

        val whereClaus = "${DatingDBContract.UserTable.USER_ID} LIKE ?"
        val whereClausArgs = arrayOf(user.id.toString())

        val deletedRows = dbWrite.delete(
            DatingDBContract.UserTable.TABLE_NAME,
            whereClaus,
            whereClausArgs
        )

        return deletedRows > 0
    }
}














