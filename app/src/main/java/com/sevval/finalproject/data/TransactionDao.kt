package com.sevval.finalproject.data

import androidx.room.*
import com.sevval.finalproject.model.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * FROM transaction_table ORDER BY id ASC")
    fun getAllTransactions():Flow<List<Transaction>>

    @Query("SELECT amount FROM transaction_table")
    fun getAmount():Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTransaction(transaction: Transaction)

}