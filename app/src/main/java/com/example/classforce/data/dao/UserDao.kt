package com.example.classforce.data.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.classforce.data.entities.UserEntity


@Dao
interface UserDao {
    @Insert
        fun insertUser(user: UserEntity)

    @Update
        fun updateUser(user: UserEntity)

    @Delete
        fun deleteUser(user: UserEntity)

    @Query("SELECT * FROM user")
        fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM user WHERE userid = :userId")
    fun getUserById(userId: Long): UserEntity?

    @Query("SELECT * FROM user WHERE username = :username")
    fun getUserByUsername(username: String): UserEntity?

    @Query("SELECT * FROM user")
    fun getAllUsersLiveData(): LiveData<List<UserEntity>>
}
