package com.example.classforce.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.classforce.R
import com.example.classforce.viewmodel.UserViewModel

class UserListActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val searchButton = findViewById<Button>(R.id.searchButton)
        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        val userDataTextView = findViewById<TextView>(R.id.userDataTextView)

        searchButton.setOnClickListener {
            val userIdOrUsername = searchEditText.text.toString()

            val user = userViewModel.getUserByIdOrUsername(userIdOrUsername)

            if (user != null) {

                    val userData = "User ID: ${user.userid}\n" +
                            "Primeiro Nome: ${user.primeiro_nome}\n" +
                            "Último nome: ${user.ultimo_nome}\n" +
                            "Username: ${user.username}\n" +
                            "Email: ${user.email}"
                    userDataTextView.text = userData
                } else {
                    userDataTextView.text = "Utilizador não encontrado"
                    val intent = Intent(this, UserListActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

