package com.example.classforce.data.entities


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val userid: Long = 0,
    var primeiro_nome: String,
    var ultimo_nome: String,
    var username: String,
    var password: String,
    var email: String
)



