package com.example.classforce.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.classforce.R
import com.example.classforce.viewmodel.CursoViewModel
import com.example.classforce.viewmodel.SharedViewModelCurso

class CursoUpdateActivity : AppCompatActivity() {

        private lateinit var cursoViewModel: CursoViewModel
        private lateinit var sharedViewModelCurso: SharedViewModelCurso

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_curso_update)

            cursoViewModel = ViewModelProvider(this).get(CursoViewModel::class.java)
            sharedViewModelCurso = ViewModelProvider(this).get(SharedViewModelCurso::class.java)


            var nomeEt = findViewById<EditText>(R.id.nomeet)
            var localEt = findViewById<EditText>(R.id.locale)
            var diEditText = findViewById<EditText>(R.id.dataInicioTextView)
            var dfEditText = findViewById<EditText>(R.id.dataFimTextView)
            var precoEt = findViewById<EditText>(R.id.precoEditText)
            var duracaoEditText = findViewById<EditText>(R.id.duracaoEditText)
            var edicaoEditText = findViewById<EditText>(R.id.edicaoEditText)
            var descricaoEditText = findViewById<EditText>(R.id.descricaoEditText)

            var cursoIdEditText = findViewById<EditText>(R.id.cursoIdEt)
            var searchButton = findViewById<Button>(R.id.searchCursoButton)

            searchButton.setOnClickListener {
                var cursoId = cursoIdEditText.text.toString()

                if (cursoId.isNotEmpty()) {
                    var curso = cursoViewModel.getCursoById2(cursoId)

                    if (curso != null) {
                        sharedViewModelCurso.curso = curso

                        nomeEt.setText(curso.nome)
                        localEt.setText(curso.local)
                        diEditText.setText(curso.dataInicio)
                        dfEditText.setText(curso.dataFim)
                        precoEt.setText(curso.preco)
                        duracaoEditText.setText(curso.duracao)
                        edicaoEditText.setText(curso.edicao)
                        descricaoEditText.setText(curso.descricao)
                    } else {
                        showRegisterConfirmationDialog()
                    }
                } else {
                    Toast.makeText(this, "Insira o ID do Curso", Toast.LENGTH_SHORT).show()
                }
            }

            var updateButton = findViewById<Button>(R.id.updateButton)
            updateButton.setOnClickListener {
                var updatedCurso = sharedViewModelCurso.curso

                if (updatedCurso != null) {
                    updatedCurso.nome = nomeEt.text.toString()
                    updatedCurso.local = localEt.text.toString()
                    updatedCurso.dataInicio = diEditText.text.toString()
                    updatedCurso.dataFim = dfEditText.text.toString()
                    updatedCurso.preco = precoEt.text.toString()
                    updatedCurso.duracao = duracaoEditText.text.toString()
                    updatedCurso.edicao = edicaoEditText.text.toString()
                    updatedCurso.descricao = descricaoEditText.text.toString()

                    cursoViewModel.updateCurso(updatedCurso)

                    Toast.makeText(this, "Dados do curso atualizados com sucesso", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Nenhum curso para atualizar", Toast.LENGTH_SHORT).show()
                }
            }
        }

        private fun showRegisterConfirmationDialog() {
            val alertDialog = AlertDialog.Builder(this)
                .setTitle("Curso Não Encontrado")
                .setMessage("O Curso Inserido não existe. Deseja registar?")
                .setPositiveButton("Sim") { dialog, _ ->
                    val intent = Intent(this, RegisterCursoActivity::class.java)
                    startActivity(intent)
                    dialog.dismiss()
                }
                .setNegativeButton("Não") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()

            alertDialog.show()
        }
    }
