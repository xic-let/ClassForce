package com.example.classforce.views

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.lifecycleScope
import com.example.classforce.R
import com.example.classforce.data.ClassForceDatabase
import com.example.classforce.data.dao.UserDao
import com.example.classforce.data.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        userDao = ClassForceDatabase.getInstance(this).userDao()

        val registerButton = findViewById<AppCompatButton>(R.id.registerButton)
        registerButton.setOnClickListener {
            val firstName = findViewById<EditText>(R.id.firstNameEditText).text.toString()
            val lastName = findViewById<EditText>(R.id.lastNameEditText).text.toString()
            val username = findViewById<EditText>(R.id.username).text.toString()
            val password = findViewById<EditText>(R.id.passwordEditText).text.toString()
            val email = findViewById<EditText>(R.id.emailEditText).text.toString()

            if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Por favor preencha todos os dados.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newUser = UserEntity(
                primeiro_nome = firstName,
                ultimo_nome = lastName,
                username = username,
                password = password,
                email = email
            )

            lifecycleScope.launch(Dispatchers.IO) {
                userDao.insertUser(newUser)


                runOnUiThread {
                    Toast.makeText(this@RegisterActivity, "Registo efetuado com sucesso!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}
