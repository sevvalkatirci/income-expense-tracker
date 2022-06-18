package com.sevval.finalproject.repo

import com.sevval.finalproject.data.TransactionDao
import com.sevval.finalproject.model.Transaction
import kotlinx.coroutines.flow.Flow

class TransactionRepository(private val transactionDao: TransactionDao) {

    val allTransactions: Flow<List<Transaction>> =transactionDao.getAllTransactions()
    val amounts:Flow<List<Int>> =transactionDao.getAmount()

    suspend fun insert(transaction: Transaction){
        transactionDao.insertTransaction(transaction)
    }

    suspend fun update(transaction: Transaction){
        transactionDao.updateTransaction(transaction)
    }

    suspend fun delete(transaction: Transaction){
        transactionDao.deleteTransaction(transaction)
    }


}