package com.lando.notesappkotlin.data.remote.net

import com.lando.notesappkotlin.data.model.user.login.LoginRequest
import com.lando.notesappkotlin.data.model.user.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRemoteApi {
    @POST("user/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}