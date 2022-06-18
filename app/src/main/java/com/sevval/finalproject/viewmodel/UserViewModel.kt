package com.sevval.finalproject.viewmodel

import androidx.lifecycle.*
import com.sevval.finalproject.model.User
import com.sevval.finalproject.repo.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository):ViewModel() {

    val allUserInfo:LiveData<List<User>> =repository.allUserInfo.asLiveData()

    fun insert(user: User)=viewModelScope.launch {
        repository.insert(user)
    }

    fun update(user: User)=viewModelScope.launch {
        repository.update(user)
    }

    fun delete(user: User)=viewModelScope.launch {
        repository.delete(user)
    }
    fun deleteAll(users:List<User>)=viewModelScope.launch {
        repository.deleteAll(users)
    }

    fun login(userName:String){
        repository.login(userName)
    }
}

class UserViewModelFactory(private val repository: UserRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(repository) as T
        }
       throw IllegalArgumentException("UnknownViewModelClass")
    }
}