package com.lando.notesappkotlin.ui.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.lando.notesappkotlin.R
import com.lando.notesappkotlin.data.model.user.login.LoginRequest
import com.lando.notesappkotlin.data.model.user.login.LoginResponse
import com.lando.notesappkotlin.data.remote.source.RetrofitBuilder
import com.lando.notesappkotlin.data.remote.source.user.UserRemoteDataSource
import com.lando.notesappkotlin.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class  LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var userAPI = UserRemoteDataSource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickLoginBtn()
    }

    private fun clickLoginBtn()
    {
        binding.btnLogin.setOnClickListener {
            val user = LoginRequest(
                binding.etMail.text.toString(),
                binding.etPassword.text.toString()
            )

            userAPI.login(user)
        }
    }
}