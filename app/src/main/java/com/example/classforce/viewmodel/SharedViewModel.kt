package com.example.classforce.viewmodel

import androidx.lifecycle.ViewModel
import com.example.classforce.data.entities.UserEntity

class SharedViewModel : ViewModel() {
    var user: UserEntity? = null
}
