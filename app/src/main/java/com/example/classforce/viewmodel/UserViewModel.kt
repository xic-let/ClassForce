package com.example.classforce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classforce.data.entities.UserEntity
import com.example.classforce.data.repository.UserRepository
import com.example.classforce.model.UserModel
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    val allUsers: LiveData<List<UserEntity>> = userRepository.getAllUsersLiveData()

    fun insertUser(user: UserEntity) {
        viewModelScope.launch {
            userRepository.insertUser(user)
        }
    }

    fun updateUser(user: UserEntity) {
        viewModelScope.launch {
            userRepository.updateUser(user)
        }
    }

    fun deleteUser(user: UserEntity) {
        viewModelScope.launch {
            userRepository.deleteUser(user)
        }
    }

    fun getUserByIdOrUsername(userIdOrUsername: String): UserEntity? {
        val userId = userIdOrUsername.toLongOrNull()
        return if (userId != null) {
            userRepository.getUserById(userId)
        } else {
            userRepository.getUserByUsername(userIdOrUsername)
        }
    }
    fun mapUserEntitiesToUserModels(userEntities: List<UserEntity>): List<UserModel> {
        return userEntities.map { userEntity ->
            UserModel(
                userid  = userEntity.userid,
                primeiro_nome = userEntity.primeiro_nome,
                ultimo_nome = userEntity.ultimo_nome,
                username = userEntity.username,
                password = userEntity.email,
                email = userEntity.email
            )
        }
    }
}
