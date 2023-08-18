package com.example.classforce.data.repository

import androidx.lifecycle.LiveData
import com.example.classforce.data.dao.UserDao
import com.example.classforce.data.entities.UserEntity
import com.example.classforce.model.UserModel

class UserRepository(private val userDao: UserDao) {

    fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }
    fun getUserByUsername(username: String): UserEntity? {
        return userDao.getUserByUsername(username)
    }

    fun getAllUsersLiveData(): LiveData<List<UserEntity>> {
        return userDao.getAllUsersLiveData()
    }

    fun getAllUsers(): List<UserModel> {
        val users = userDao.getAllUsers()
        return users.map { it.toUserModel() }
    }

    fun getUserById(userId: Long): UserEntity?  {
        return userDao.getUserById(userId)
    }

    fun updateUser(user: UserEntity) {
        userDao.updateUser(user)
    }

    fun deleteUser(user: UserEntity) {
        userDao.deleteUser(user)
    }

    private fun UserEntity.toUserModel(): UserModel {
        return UserModel(
            userid = userid,
            primeiro_nome = primeiro_nome,
            ultimo_nome = ultimo_nome,
            username = username,
            password = password,
            email = email
        )
    }
}

