package com.sevval.finalproject

import android.app.Application
import com.sevval.finalproject.data.TransactionRoomDatabase
import com.sevval.finalproject.data.UserRoomDatabase
import com.sevval.finalproject.repo.TransactionRepository
import com.sevval.finalproject.repo.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TransactionApplication:Application() {
    val applicationScope= CoroutineScope(SupervisorJob())

    val database by lazy { TransactionRoomDatabase.getDatabase(this) }
    val repository by lazy { TransactionRepository(database.transactionDao()) }

    val userDatabase by lazy { UserRoomDatabase.userDatabase(this) }
    val userRepo by lazy { UserRepository(userDatabase.userDao()) }
}