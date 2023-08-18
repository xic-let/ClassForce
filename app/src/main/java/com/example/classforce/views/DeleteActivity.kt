package com.example.classforce.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.classforce.R
import com.example.classforce.data.ClassForceDatabase
import com.example.classforce.data.repository.UserRepository
import com.example.classforce.viewmodel.UserViewModel
import com.example.classforce.viewmodel.UserViewModelFactory

class DeleteActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        val userDao = ClassForceDatabase.getInstance(this).userDao()
        val userRepository = UserRepository(userDao)
        userViewModel = ViewModelProvider(this, UserViewModelFactory(userRepository)).get(UserViewModel::class.java)

        val userIdOrUsernameEditText = findViewById<EditText>(R.id.userIdOrUsernameEditText)
        val searchButton = findViewById<Button>(R.id.searchButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)

        searchButton.setOnClickListener {
            val userIdOrUsername = userIdOrUsernameEditText.text.toString()
            val user = userViewModel.getUserByIdOrUsername(userIdOrUsername)

            val userDataTextView = findViewById<TextView>(R.id.userDataTextView)

            if (user != null) {
                val userData = "ID: ${user.userid}\n" +
                        "Primeiro Nome: ${user.primeiro_nome}\n" +
                        "Último Nome: ${user.ultimo_nome}\n" +
                        "Username: ${user.username}\n" +
                        "Email: ${user.email}"
                userDataTextView.text = userData
            } else {
                userDataTextView.text = "Utilizador não encontrado"
                val intent = Intent(this, DeleteActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        deleteButton.setOnClickListener {
            val userIdOrUsername = userIdOrUsernameEditText.text.toString()
            val user = userViewModel.getUserByIdOrUsername(userIdOrUsername)

            if (user != null) {
                userViewModel.deleteUser(user)
                Toast.makeText(this@DeleteActivity, "Utilizador Apagado Com Sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@DeleteActivity, "Utilizador Não Encontrado", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
