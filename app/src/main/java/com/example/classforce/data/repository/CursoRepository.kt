package com.example.classforce.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.example.classforce.data.dao.CursoDao
import com.example.classforce.data.entities.CursoEntity
import com.example.classforce.model.CursoModel

class CursoRepository(private val cursoDao: CursoDao) {

    val allCursos: LiveData<List<CursoModel>> = cursoDao.getAllCursos().map { cursoList ->
        cursoList.map { it.toModel() }
    }

    fun getCursoById(cursoId: Long): LiveData<CursoModel?> {
        return cursoDao.getCursoByIdLiveData(cursoId).switchMap { curso ->
            if (curso != null) {
                MutableLiveData(curso.toModel())
            } else {
                MutableLiveData(null)
            }
        }
    }
   fun getAllCursosLiveData(): LiveData<List<CursoModel>> {
        return cursoDao.getAllCursos().map { cursoList ->
            cursoList.map { it.toModel() }
        }
    }

    fun getCursoById2(cursoId: Long): CursoEntity? {
        return cursoDao.getCursoById2(cursoId)
    }

    fun updateCurso(curso: CursoEntity) {
        cursoDao.updateCurso(curso)
    }

    fun refreshCursos(cursos: List<CursoEntity>) {
        cursoDao.deleteAllCursos()
        cursoDao.insertAllCursos(cursos)
    }

    fun insertCurso(curso: CursoEntity) {
        cursoDao.insertCurso(curso)
    }

    fun deleteCurso(curso: CursoEntity) {
        cursoDao.deleteCurso(curso)
    }

    private fun CursoEntity.toModel(): CursoModel {
        return CursoModel(
            courseid,
            nome,
            local,
            dataInicio,
            dataFim,
            preco,
            duracao,
            edicao,
            descricao
        )
    }

    private fun CursoModel.toEntity(): CursoEntity {
        return CursoEntity(
            courseid,
            nome,
            local,
            dataInicio,
            dataFim,
            preco,
            duracao,
            edicao,
            descricao
        )
    }
}
