package com.example.classforce.data.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.classforce.R
import com.example.classforce.data.ClassForceDatabase
import com.example.classforce.data.repository.CursoRepository
import com.example.classforce.viewmodel.CursoViewModel
import com.example.classforce.viewmodel.CursoViewModelFactory
import com.example.classforce.views.CursoDeleteActivity
import com.example.classforce.views.CursoListActivity
import com.example.classforce.views.CursoUpdateActivity
import com.example.classforce.views.RegisterCursoActivity

class CursoFragment : Fragment() {
    private lateinit var cursoViewModel: CursoViewModel


        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_curso, container, false)

            val cursoDao = ClassForceDatabase.getInstance(requireContext()).cursoDao()
            val cursoRepository = CursoRepository(cursoDao)

            cursoViewModel = ViewModelProvider(
                this,
                CursoViewModelFactory(cursoRepository)
            ).get(CursoViewModel::class.java)

            view.findViewById<View>(R.id.btnInsert).setOnClickListener {
                val intent = Intent(requireContext(), RegisterCursoActivity::class.java)
                startActivity(intent)
            }

            view.findViewById<Button>(R.id.btnUpdate).setOnClickListener {
                val intent = Intent(requireContext(), CursoUpdateActivity::class.java)
                startActivity(intent)
            }

            view.findViewById<Button>(R.id.btnDelete).setOnClickListener {
                val intent = Intent(requireContext(), CursoDeleteActivity::class.java)
                startActivity(intent)
            }

            view.findViewById<Button>(R.id.btnGetById).setOnClickListener {
                val intent = Intent(requireContext(), CursoListActivity::class.java)
                startActivity(intent)
            }


            return view
        }

}
