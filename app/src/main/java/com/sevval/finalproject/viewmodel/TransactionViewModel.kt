package com.sevval.finalproject.viewmodel

import androidx.lifecycle.*
import com.sevval.finalproject.model.Transaction
import com.sevval.finalproject.repo.TransactionRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class TransactionViewModel(private val repository: TransactionRepository):ViewModel() {
    val allTransactions:LiveData<List<Transaction>> =repository.allTransactions.asLiveData()
    val amounts:LiveData<List<Int>> =repository.amounts.asLiveData()

    fun insert(transaction: Transaction)=viewModelScope.launch {
        repository.insert(transaction)
    }

    fun update(transaction: Transaction)=viewModelScope.launch {
        repository.update(transaction)
    }

    fun delete(transaction: Transaction)=viewModelScope.launch {
        repository.delete(transaction)
    }
}

class TransactionViewModelFactory(private val repository: TransactionRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TransactionViewModel::class.java)){
            return TransactionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
