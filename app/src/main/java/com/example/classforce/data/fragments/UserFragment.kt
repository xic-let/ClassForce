package com.example.classforce.data.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.classforce.R
import com.example.classforce.data.ClassForceDatabase
import com.example.classforce.data.repository.UserRepository
import com.example.classforce.viewmodel.UserViewModel
import com.example.classforce.viewmodel.UserViewModelFactory
import com.example.classforce.views.DeleteActivity
import com.example.classforce.views.RegisterActivity
import com.example.classforce.views.UpdateActivity
import com.example.classforce.views.UserFullListActivity
import com.example.classforce.views.UserListActivity


class UserFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userDao = ClassForceDatabase.getInstance(requireContext()).userDao()
        val userRepository = UserRepository(userDao)
        userViewModel = ViewModelProvider(this, UserViewModelFactory(userRepository)).get(
            UserViewModel::class.java
        )

        val btnInsert = view.findViewById<View>(R.id.btnInsertuser)
        val btnUpdate = view.findViewById<View>(R.id.btnUpdateuser)
        val btnDelete = view.findViewById<View>(R.id.btnDeleteuser)
        val btnGetById = view.findViewById<View>(R.id.btnGetByIduser)
        val btnGetByUsername = view.findViewById<View>(R.id.btnGetByUsernameuser)
        val btnListAll = view.findViewById<View>(R.id.btnListAlluser)


        btnInsert.setOnClickListener {
            val intent = Intent(requireContext(), RegisterActivity::class.java)
            startActivity(intent)
        }

        btnUpdate.setOnClickListener {
            val intent = Intent(requireContext(), UpdateActivity::class.java)
            startActivity(intent)
        }

        btnDelete.setOnClickListener {
            val intent = Intent(requireContext(), DeleteActivity::class.java)
            startActivity(intent)
        }

        btnGetById.setOnClickListener {
            val intent = Intent(requireContext(), UserListActivity::class.java)
            startActivity(intent)
        }

        btnGetByUsername.setOnClickListener {
            val intent = Intent(requireContext(), UserListActivity::class.java)
            startActivity(intent)
        }

        btnListAll.setOnClickListener {
            val intent = Intent(requireContext(), UserFullListActivity::class.java)
            startActivity(intent)
        }
    }
}