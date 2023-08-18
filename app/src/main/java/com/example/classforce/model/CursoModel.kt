package com.example.classforce.model


data class CursoModel(
        val courseid: Long = 0,
        val nome: String,
        val local: String,
        val dataInicio: String,
        val dataFim: String,
        val preco: String?,
        val duracao: String,
        val edicao: String,
        val descricao: String
)



