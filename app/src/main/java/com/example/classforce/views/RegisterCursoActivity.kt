package com.example.classforce.views


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.classforce.R
import com.example.classforce.data.ClassForceDatabase
import com.example.classforce.data.dao.CursoDao
import com.example.classforce.data.entities.CursoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterCursoActivity : AppCompatActivity() {

    private lateinit var cursoDao: CursoDao
    private var selectedImage: ByteArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_curso_register)

        cursoDao = ClassForceDatabase.getInstance(this).cursoDao()


        val registerButton = findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener {
            val nomeCurso = findViewById<EditText>(R.id.nomeet).text.toString()
            val local = findViewById<EditText>(R.id.locale).text.toString()
            val dataInicio = findViewById<EditText>(R.id.dataInicioTextView).text.toString()
            val dataFim = findViewById<EditText>(R.id.dataFimTextView).text.toString()
            val preco = findViewById<EditText>(R.id.precoEditText).text.toString()
            val duracao = findViewById<EditText>(R.id.duracaoEditText).text.toString()
            val edicao = findViewById<EditText>(R.id.edicaoEditText).text.toString()
            val descricao = findViewById<EditText>(R.id.descricaoEditText).text.toString()

            if (nomeCurso.isEmpty() || local.isEmpty() || dataInicio.isEmpty() || dataFim.isEmpty() || preco.isEmpty() || duracao.isEmpty() || edicao.isEmpty() || descricao.isEmpty()) {
                Toast.makeText(this, "Por favor preencha todos os dados.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newCurso = CursoEntity(
                nome = nomeCurso,
                local = local,
                dataInicio = dataInicio,
                dataFim = dataFim,
                preco = preco,
                duracao = duracao,
                edicao = edicao,
                descricao = descricao
            )

            GlobalScope.launch(Dispatchers.IO) {
                cursoDao.insertCurso(newCurso)
                runOnUiThread {
                    Toast.makeText(
                        this@RegisterCursoActivity,
                        "Registo de curso efectuado com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(this@RegisterCursoActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun openImageChooser() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        resultLauncher.launch(intent)
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            if (data != null) {
                val imageUri = data.data
                val inputStream = contentResolver.openInputStream(imageUri!!)
                val imageBytes = inputStream?.readBytes()

                selectedImage = imageBytes
            }
        }
    }
}
