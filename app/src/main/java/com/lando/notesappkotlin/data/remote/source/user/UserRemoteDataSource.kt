package com.lando.notesappkotlin.data.remote.source.user

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.lando.notesappkotlin.data.model.user.login.LoginRequest
import com.lando.notesappkotlin.data.model.user.login.LoginResponse
import com.lando.notesappkotlin.data.remote.net.UserRemoteApi
import com.lando.notesappkotlin.data.remote.source.RetrofitBuilder
import com.lando.notesappkotlin.ui.views.LoginActivity
import com.lando.notesappkotlin.ui.views.NotesActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class UserRemoteDataSource
{
    val service: UserRemoteApi = RetrofitBuilder.getRetrofit().create(UserRemoteApi::class.java)

    fun login(user: LoginRequest)
    {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.login(user)

            withContext(Dispatchers.Main)
            {
                try
                {
                    if (response.isSuccessful)
                    {
                        /*val loginData = response.body()
                        val intent = Intent(this@LoginActivity, NotesActivity::class.java)

                        if (loginData != null)
                            intent.putExtra("user_id", loginData.loginData.userID)

                        startActivity(intent)*/
                    }
                }
                catch (error: HttpException)
                {
                    print(error)
                }
            }
        }

        /*service.login(user).enqueue(object: Callback<LoginResponse> {
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
        })*/
    }
}