package com.lando.notesappkotlin.data.remote.net

import com.lando.notesappkotlin.data.model.user.login.LoginRequest
import com.lando.notesappkotlin.data.model.user.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRemoteApi {
    @POST("user/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}