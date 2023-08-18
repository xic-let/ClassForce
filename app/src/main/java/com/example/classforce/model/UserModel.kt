package com.example.classforce.model


data class UserModel(
    val userid: Long?,
    val primeiro_nome: String,
    val ultimo_nome: String,
    val username: String?,
    val password: String?,
    val email: String?
)

