package com.lando.notesappkotlin.data.remote.net

import com.lando.notesappkotlin.data.model.note.CreateNote
import com.lando.notesappkotlin.data.model.note.Note
import com.lando.notesappkotlin.data.model.note.NoteDeleted
import com.lando.notesappkotlin.data.model.note.UpdateNote
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface NoteRemoteApi {
    @GET("note")
    fun getAllNotes(): Call<List<Note>>

    @GET("note/{id}")
    suspend fun getUserNotes(
        @Path("id") id: Int
    ): Response<ArrayList<Note>>

    @POST("note")
    suspend fun addNote(@Body note: CreateNote): Response<CreateNote>

    @PUT("note")
    suspend fun updateNote(@Body note: UpdateNote): Response<UpdateNote>

    @DELETE("note/{id}")
    suspend fun deleteNote(@Path("id") id: Int): Response<NoteDeleted>
}