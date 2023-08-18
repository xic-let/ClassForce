package com.example.classforce.data

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.classforce.data.entities.CursoEntity
import com.example.classforce.data.entities.UserEntity
import com.example.classforce.model.CursoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class DatabaseInitializer(private val database: ClassForceDatabase, private val context: Context) {

    suspend fun initializeCursosData() = withContext(Dispatchers.IO) {
        val cursoDao = database.cursoDao()

        val curso1 = CursoEntity(
            courseid = 1,
            nome = "Software Developer",
            local = "Porto",
            dataInicio = "01/10/2023",
            dataFim = "31/03/2024",
            preco = "Gratuito",
            duracao = "1000Horas",
            edicao = "4a Edição",
            descricao = "Curso de reconversão profissional para a área de IT"

        )
        val curso2 = CursoEntity(
            courseid = 2,
            nome = "Gestão de Redes Sociais",
            local = "Porto",
            dataInicio = "01/10/2023",
            dataFim = "31/03/2024",
            preco = "500€",
            duracao = "1000Horas",
            edicao = "2a Edição",
            descricao = "Curso de reconversão profissional para a área de IT"

        )

        cursoDao.insertCurso(curso1)
        cursoDao.insertCurso(curso2)
    }



    suspend fun initializeUsersData()= withContext(Dispatchers.IO) {
        val userDao = database.userDao()

        val user1 = UserEntity(
            primeiro_nome = "João",
            ultimo_nome = "Coelho",
            username = "Jota1",
            password = "Pass",
            email = "Jota1@cesae.pt",
        )

        val user2 = UserEntity(
            primeiro_nome = "Ana",
            ultimo_nome = "Cunha",
            username = "Ana1",
            password = "Pass1",
            email = "Ana1@cesae.pt",
        )

        val user3 = UserEntity(
            primeiro_nome = "Patricia",
            ultimo_nome = "Cunha",
            username = "admin",
            password = "Pass",
            email = "Ana2@cesae.pt",
        )


        userDao.insertUser(user1)
        userDao.insertUser(user2)
        userDao.insertUser(user3)
    }


    suspend fun getCursosFromDatabase(owner: LifecycleOwner): LiveData<List<CursoModel>> {
        val cursoDao = database.cursoDao()
        val cursoEntitiesLiveData = cursoDao.getAllCursos()

        val cursosListLiveData = MutableLiveData<List<CursoModel>>()

        cursoEntitiesLiveData.observe(owner) { cursoEntities ->
            val cursosList = cursoEntities.map { cursoEntity ->
                CursoModel(
                    courseid = cursoEntity.courseid,
                    nome = cursoEntity.nome,
                    local = cursoEntity.local,
                    dataInicio = cursoEntity.dataInicio,
                    dataFim = cursoEntity.dataFim,
                    preco = cursoEntity.preco,
                    duracao = cursoEntity.duracao,
                    edicao = cursoEntity.edicao,
                    descricao = cursoEntity.descricao

                )
            }
            cursosListLiveData.postValue(cursosList)
        }

        return cursosListLiveData
    }


}
