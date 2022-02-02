package com.lando.notesappkotlin.data.remote.source.note

import com.lando.notesappkotlin.data.model.note.CreateNote
import com.lando.notesappkotlin.data.model.note.UpdateNote
import com.lando.notesappkotlin.data.remote.net.NoteRemoteApi
import com.lando.notesappkotlin.data.remote.source.RetrofitBuilder.getRetrofit
import com.lando.notesappkotlin.ui.adapters.NoteAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class NoteRemoteDataSource {
    private val service: NoteRemoteApi = getRetrofit().create(NoteRemoteApi::class.java)

    fun getUserNotes(userID: Int, noteAdapter: NoteAdapter)
    {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getUserNotes(userID)

            print(response)

            withContext(Dispatchers.Main)
            {
                try
                {
                    if (response.isSuccessful)
                    {
                        response.body()?.let {
                                noteData -> noteAdapter.setData(noteData)
                        }
                    }
                }
                catch (error: HttpException)
                {
                    print(error)
                }
            }
        }
    }

    fun createNote(noteData: CreateNote, userID: Int, noteAdapter: NoteAdapter)
    {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.addNote(noteData)

            withContext(Dispatchers.Main)
            {
                try
                {
                    if (response.isSuccessful)
                    {
                        getUserNotes(userID, noteAdapter)
                        //Toast.makeText(notesActivity, "New note created", Toast.LENGTH_LONG).show()
                    }
                }
                catch (error: HttpException)
                {
                    print(error)
                    //Toast.makeText(notesActivity, "Error: $error", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun updateNote(updatedData: UpdateNote)
    {
        CoroutineScope(Dispatchers.IO).launch {
            service.updateNote(updatedData)
        }
    }

    fun deleteNote(noteID: Int)
    {
        CoroutineScope(Dispatchers.IO).launch {
            service.deleteNote(noteID)
        }
    }
}