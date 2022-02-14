package com.reza.roomapplication.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.reza.roomapplication.db.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM User where username like :username")
    fun getUser(username: String) : User

    @Query("SELECT * FROM User where username like :username")
    fun getUserLive(username: String) : LiveData<User>

    @Query("SELECT COUNT(*) FROM User where username like :username and password like :password")
    fun authenticate(username: String, password: String) : Int

    @Query("SELECT * FROM User where id = :id")
    fun getUser(id: Long) : LiveData<User>

    @Insert()
    fun insert( user: User):Long

    @Delete
    fun delete(vararg user: User)

    @Update
    fun update(vararg user: User)


}