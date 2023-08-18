package com.example.classforce.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.classforce.R
import com.example.classforce.data.ClassForceDatabase
import com.example.classforce.data.dao.CursoDao
import com.example.classforce.data.repository.CursoRepository
import com.example.classforce.viewmodel.CursoViewModel
import com.example.classforce.viewmodel.CursoViewModelFactory
import kotlinx.coroutines.launch

class CursoDeleteActivity: AppCompatActivity() {
    private lateinit var cursoViewModel: CursoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_curso_delete)

        val cursoDao: CursoDao = ClassForceDatabase.getInstance(this).cursoDao()
        val cursoRepository = CursoRepository(cursoDao)
        cursoViewModel = ViewModelProvider(this, CursoViewModelFactory(cursoRepository)).get(CursoViewModel::class.java)


        val cursoIdEditText = findViewById<EditText>(R.id.cursoIdEditText)
        val searchButton = findViewById<Button>(R.id.searchButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)

        searchButton.setOnClickListener {
            val cursoId = cursoIdEditText.text.toString()
            lifecycleScope.launch {
                val curso = cursoViewModel.getCursoById2(cursoId)
                val cursoDataTextView = findViewById<TextView>(R.id.cursoDataTextView)

                if (curso != null) {
                    val cursoData = "ID: ${curso.courseid}\n" + "Nome: ${curso.nome}\n" +
                            "Local: ${curso.local}\n" +
                            "Data inicio: ${curso.dataInicio}\n" +
                            "Data fim: ${curso.dataFim}\n" +
                            "Preço: ${curso.preco}" +
                            "Duração: ${curso.duracao}\n" +
                            "Edição: ${curso.edicao}\n" +
                            "Descrição: ${curso.descricao}\n"


                    cursoDataTextView.text = cursoData
                } else {
                    cursoDataTextView.text = "Curso não encontrado"
                    val intent =
                        Intent(this@CursoDeleteActivity, CursoDeleteActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            deleteButton.setOnClickListener {
                val cursoId = cursoIdEditText.text.toString()

                lifecycleScope.launch {
                    val curso = cursoViewModel.getCursoById2(cursoId)

                    if (curso != null) {
                        cursoViewModel.deleteCurso(curso)
                        Toast.makeText(
                            this@CursoDeleteActivity,
                            "Curso Apagado Com Sucesso",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@CursoDeleteActivity,
                            "Curso Não Encontrado",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent =
                            Intent(this@CursoDeleteActivity, CursoDeleteActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }
}

