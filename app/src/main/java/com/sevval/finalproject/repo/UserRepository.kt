package com.sevval.finalproject.repo

import com.sevval.finalproject.data.UserDao
import com.sevval.finalproject.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val allUserInfo: Flow<List<User>> =userDao.getAllUSerInfo()
    val userNameList:Flow<List<String>> =userDao.getUserName()
    val userPasswordList:Flow<List<String>> =userDao.getUserPassword()

    suspend fun insert(user: User){
        userDao.insertUser(user)
    }

    suspend fun update(user: User){
        userDao.updateUser(user)
    }

    suspend fun delete(user: User){
        userDao.deleteUser(user)
    }
    suspend fun deleteAll(users:List<User>){
        userDao.deleteAll(users)
    }
    fun login(userName:String){
        userDao.getUser(userName)
    }
}