package com.example.classforce.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.classforce.data.entities.CursoEntity


@Dao
interface CursoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCursos(cursos: List<CursoEntity>)

    @Insert
    fun insertCurso(curso: CursoEntity)

    @Update
     fun updateCurso(curso: CursoEntity)

    @Delete
     fun deleteCurso(curso: CursoEntity)

    @Query("DELETE FROM cursos")
     fun deleteAllCursos()

    @Query("SELECT * FROM cursos")
    fun getAllCursos(): LiveData<List<CursoEntity>>

    @Query("SELECT * FROM cursos WHERE courseid = :cursoId")
    fun getCursoByIdLiveData(cursoId: Long): LiveData<CursoEntity?>

    @Query("SELECT * FROM cursos WHERE courseId = :cursoId")
    fun getCursoById2(cursoId: Long): CursoEntity?

}

