package com.lando.notesappkotlin.data.remote.source.user

import android.content.Context
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

    fun login(user: LoginRequest, context: Context)
    {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.login(user)

            withContext(Dispatchers.Main)
            {
                try
                {
                    if (response.isSuccessful)
                    {
                        val loginData = response.body()
                        val intent = Intent(context, NotesActivity::class.java)

                        //print("LOGIN DATA: $loginData")

                        if (loginData != null)
                            intent.putExtra("user_id", loginData.loginData.userID)

                        context.startActivity(intent)
                    }
                }
                catch (error: HttpException)
                {
                    print(error)
                }
            }
        }
    }
}