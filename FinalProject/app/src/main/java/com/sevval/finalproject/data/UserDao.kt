package com.sevval.finalproject.data

import androidx.room.*
import com.sevval.finalproject.model.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Query("SELECT * FROM userinfo ORDER BY id ASC ")
    fun getAllUSerInfo(): Flow<List<User>>

    @Query("SELECT name FROM userinfo")
    fun getUserName(): Flow<List<String>>

    @Query("SELECT * FROM userinfo WHERE name IN (:userName)")
    fun getUser(userName:String):User



    @Query("SELECT password FROM userinfo")
    fun getUserPassword(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user:User)

    @Delete
    suspend fun deleteAll(users:List<User>)

    @Delete
    suspend fun deleteUser(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(user: User)
}