package com.example.classforce.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.classforce.data.repository.CursoRepository


class CursoViewModelFactory(private val cursoRepository: CursoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CursoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CursoViewModel(cursoRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

