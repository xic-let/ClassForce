package com.example.classforce.data.fragments

import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import com.example.classforce.R

class NavHostFragment : NavHostFragment() {
    override fun onCreateNavHostController(navHostController: NavHostController) {
        super.onCreateNavHostController(navHostController)

        val navGraph = navHostController.navInflater.inflate(R.navigation.nav_graph)
        navHostController.graph = navGraph
    }
}