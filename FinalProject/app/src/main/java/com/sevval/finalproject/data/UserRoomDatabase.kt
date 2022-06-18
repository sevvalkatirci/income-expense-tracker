package com.sevval.finalproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sevval.finalproject.model.User


@Database(
    entities = arrayOf(User::class),
    version = 1,
    exportSchema = false
)
abstract class UserRoomDatabase:RoomDatabase() {

    abstract fun userDao():UserDao

    companion object{

        @Volatile
        private var INSTANCE:UserRoomDatabase?=null

        fun userDatabase(context: Context):UserRoomDatabase{
            return INSTANCE?: synchronized(this){
                val instance=Room.databaseBuilder(
                        context.applicationContext,
                UserRoomDatabase::class.java,
                "user_database").build()

                INSTANCE=instance
                instance
            }
        }
    }
}