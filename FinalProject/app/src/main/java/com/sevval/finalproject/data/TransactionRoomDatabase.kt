package com.sevval.finalproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sevval.finalproject.model.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = arrayOf(Transaction::class),
    version = 1,
    exportSchema = false
)
abstract class TransactionRoomDatabase:RoomDatabase() {

    abstract fun transactionDao():TransactionDao

    companion object{
        @Volatile
        private var INSTANCE:TransactionRoomDatabase?=null
        fun getDatabase(context:Context):TransactionRoomDatabase{
            return INSTANCE?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    TransactionRoomDatabase::class.java,
                    "transaction_database"
                ).build()
                //addCallback(WordDatabaseCallback(scope)).build()

                INSTANCE=instance
                instance
            }
        }
    }
}