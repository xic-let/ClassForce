package com.example.classforce.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.classforce.R
import com.example.classforce.viewmodel.CursoViewModel
import kotlinx.coroutines.launch

class CursoListActivity : AppCompatActivity() {

    private lateinit var cursoViewModel: CursoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_curso_list)

        cursoViewModel = ViewModelProvider(this).get(CursoViewModel::class.java)

        val searchButton = findViewById<Button>(R.id.csearchButton)
        val searchEditText = findViewById<EditText>(R.id.csearchEditText)
        val cursoDataTextView = findViewById<TextView>(R.id.cursoDataTextView)

        searchButton.setOnClickListener {
            val cursoId = searchEditText.text.toString()

            lifecycleScope.launch {
                val curso = cursoViewModel.getCursoById2(cursoId)


                if (curso != null) {

                    val cursoData = " ID: ${curso.courseid}\n" +
                            "Nome: ${curso.nome}\n" +
                            "Local: ${curso.local}\n" +
                            "Data de incio: ${curso.dataInicio}\n" +
                            "Data de Fim: ${curso.dataFim}\n" +
                            "Data de incio: ${curso.dataInicio}\n" +
                            "Preço: ${curso.preco}\n" +
                            "Duração: ${curso.duracao}\n" +
                            "Edição: ${curso.edicao}\n" +
                            "Descrição: ${curso.descricao}\n"



                    cursoDataTextView.text = cursoData
                } else {
                    cursoDataTextView.text = "Curso não encontrado"
                    val intent = Intent(this@CursoListActivity, CursoListActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

        }
    }
}
