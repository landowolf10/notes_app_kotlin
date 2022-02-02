package com.lando.notesappkotlin.ui.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.lando.notesappkotlin.R
import com.lando.notesappkotlin.data.model.user.login.LoginRequest
import com.lando.notesappkotlin.data.model.user.login.LoginResponse
import com.lando.notesappkotlin.data.remote.source.RetrofitBuilder
import com.lando.notesappkotlin.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class  LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickLoginBtn()
    }

    private fun login(user: LoginRequest)
    {
        RetrofitBuilder.userService.login(user).enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                val loginData = response.body()

                Log.d("Request", user.toString())
                Log.d("Response", loginData.toString())

                if(loginData?.loginData != null)
                {
                    val intent = Intent(this@LoginActivity, NotesActivity::class.java)
                    intent.putExtra("user_id", loginData.loginData.userID)
                    startActivity(intent)
                }
                else
                    Toast.makeText(applicationContext, "Invalid credentials, please verify", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "An error occurred!", Toast.LENGTH_LONG).show()
                Log.e("Error", t.message.toString())
            }
        })
    }

    private fun clickLoginBtn()
    {
        binding.btnLogin.setOnClickListener {
            val user = LoginRequest(
                binding.etMail.text.toString(),
                binding.etPassword.text.toString()
            )

            login(user)
        }
    }
}