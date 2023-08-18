package com.example.classforce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classforce.data.entities.CursoEntity
import com.example.classforce.data.repository.CursoRepository
import com.example.classforce.model.CursoModel
import kotlinx.coroutines.launch

class CursoViewModel(private val cursoRepository: CursoRepository) : ViewModel() {

    val allCursos: LiveData<List<CursoModel>> = cursoRepository.getAllCursosLiveData()

    fun insertCurso(curso: CursoEntity) {
        viewModelScope.launch {
            cursoRepository.insertCurso(curso)
        }
    }

    fun updateCurso(curso: CursoEntity) {
        viewModelScope.launch {
            cursoRepository.updateCurso(curso)
        }
    }

    fun deleteCurso(curso: CursoEntity) {
        viewModelScope.launch {
            cursoRepository.deleteCurso(curso)
        }
    }


   fun getCursoById2(cursoId: String): CursoEntity? {
        val cursoIdLong = cursoId.toLongOrNull()

        return if (cursoIdLong != null) {
            cursoRepository.getCursoById2(cursoIdLong)
        } else {
            null
        }
    }
}






