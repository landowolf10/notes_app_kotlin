package com.lando.notesappkotlin.data.remote.source

import com.lando.notesappkotlin.data.remote.net.UserRemoteApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {
    private const val BASE_URL = "http://10.0.2.2:3000/"
    private val interceptor = HttpLoggingInterceptor()

    fun getRetrofit(): Retrofit
    {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(100, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //val apiService: wNoteRemoteApi = getRetrofit().create(NoteRemoteApi::class.java)
    val userService: UserRemoteApi = getRetrofit().create(UserRemoteApi::class.java)
}