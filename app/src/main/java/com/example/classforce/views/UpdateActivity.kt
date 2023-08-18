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
import com.example.classforce.viewmodel.SharedViewModel
import com.example.classforce.viewmodel.UserViewModel

class UpdateActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        val firstNameEditText = findViewById<EditText>(R.id.firstNameEditText)
        val lastNameEditText = findViewById<EditText>(R.id.lastNameEditText)
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)


        var userIdEditText = findViewById<EditText>(R.id.userIdOrUsernameEditText)
        var searchButton = findViewById<Button>(R.id.searchButton)

        searchButton.setOnClickListener {
            var userIdOrUsername = userIdEditText.text.toString()

            if (userIdOrUsername.isNotEmpty()) {
                val user = userViewModel.getUserByIdOrUsername(userIdOrUsername)

                if (user != null) {
                    sharedViewModel.user = user


                    firstNameEditText.setText(user.primeiro_nome)
                    lastNameEditText.setText(user.ultimo_nome)
                    usernameEditText.setText(user.username)
                    passwordEditText.setText(user.password)
                    emailEditText.setText(user.email)
                } else {
                    showRegisterConfirmationDialog()
                }
            } else {
                Toast.makeText(this, "Insira ID or username", Toast.LENGTH_SHORT).show()
            }
        }


        val updateButton = findViewById<Button>(R.id.updateButton)
        updateButton.setOnClickListener {
            var updatedUser = sharedViewModel.user

            if (updatedUser != null) {
                updatedUser.primeiro_nome = firstNameEditText.text.toString()
                updatedUser.ultimo_nome = lastNameEditText.text.toString()
                updatedUser.username = usernameEditText.text.toString()
                updatedUser.password = passwordEditText.text.toString()
                updatedUser.email = emailEditText.text.toString()

                userViewModel.updateUser(updatedUser)

                Toast.makeText(this, "Dados atualizados com sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Nenhum utilizador para atualizar", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun showRegisterConfirmationDialog() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Utilizador Não Encontrado")
            .setMessage("O Utilizador Inserido não existe. Deseja registar??")
            .setPositiveButton("Sim") { dialog, _ ->
                val registerActivityClass = RegisterActivity::class.java
                val intent = Intent(this, registerActivityClass)
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




