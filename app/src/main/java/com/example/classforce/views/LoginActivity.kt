package com.example.classforce.views


import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.classforce.R
import com.example.classforce.data.ClassForceDatabase
import com.example.classforce.data.dao.UserDao
import com.example.classforce.databinding.ActivityLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDao = ClassForceDatabase.getInstance(this).userDao()

        binding.topLinearLayout.animation = AnimationUtils.loadAnimation(this, R.anim.bottom_down)
        val fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        binding.root.apply {
            binding.cardView.animation = fade_in
            binding.cardView2.animation = fade_in
            binding.classforce.animation = fade_in
            binding.textview2.animation = fade_in
            binding.registerLayout.animation = fade_in
        }


        binding.login.setOnClickListener {
            val username = binding.usernameet.text.toString()
            val password = binding.passwordet.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                showToast("Insira o nome de usuário e a senha")
                return@setOnClickListener
            }

            GlobalScope.launch(Dispatchers.IO) {
                val user = userDao.getUserByUsername(username)
                runOnUiThread {
                    if (user != null && user.password.equals(password, ignoreCase = true)) {
                        showToast("Login efetuado com sucesso")
                        startMainActivity()
                    } else {
                        showToast("Nome de usuário ou senha incorretos")
                    }
                }
            }
        }

        binding.registertxt.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}