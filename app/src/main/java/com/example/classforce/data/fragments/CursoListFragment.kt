package com.example.classforce.data.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.classforce.R
import com.example.classforce.adapter.CursoAdapter
import com.example.classforce.data.ClassForceDatabase
import com.example.classforce.data.entities.CursoEntity
import com.example.classforce.model.CursoModel

class CursoListFragment : Fragment() {
    private lateinit var cursoAdapter: CursoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_curso_details, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        cursoAdapter = CursoAdapter()
        recyclerView.adapter = cursoAdapter

        loadCursosFromDatabase()

        return view
    }

    private fun loadCursosFromDatabase() {
        val cursoDao = ClassForceDatabase.getInstance(requireContext()).cursoDao()
        val cursosLiveData: LiveData<List<CursoEntity>> = cursoDao.getAllCursos()

        cursosLiveData.observe(viewLifecycleOwner, { cursosEntities ->
            val cursosList = cursosEntities.map { cursoEntity ->
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

            cursoAdapter.updateData(cursosList)
        })
    }
}

