package com.example.classforce.views

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.classforce.R
import com.example.classforce.data.entities.UserEntity
import com.example.classforce.model.UserModel
import com.example.classforce.viewmodel.UserViewModel

class UserFullListActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var userListAdapter: ArrayAdapter<UserModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_list_view)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val userListView: ListView = findViewById(R.id.fragmentListView)
        userListAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        userListView.adapter = userListAdapter

        userViewModel.allUsers.observe(this) { userEntities ->
            val userModels = mapUserEntitiesToUserModels(userEntities)
            userListAdapter.clear()
            userListAdapter.addAll(userModels)
        }

        userListView.setOnItemClickListener { _, _, position, _ ->
            val selectedUser = userListAdapter.getItem(position)
            Toast.makeText(
                this@UserFullListActivity,
                "Plim!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun mapUserEntitiesToUserModels(userEntities: List<UserEntity>): List<UserModel> {

        return userEntities.map { userEntity ->
            UserModel(
                userid = userEntity.userid,
                primeiro_nome = userEntity.primeiro_nome,
                ultimo_nome = userEntity.ultimo_nome,
                username = userEntity.username,
                password = userEntity.password,
                email = userEntity.email

            )
        }
    }
}
