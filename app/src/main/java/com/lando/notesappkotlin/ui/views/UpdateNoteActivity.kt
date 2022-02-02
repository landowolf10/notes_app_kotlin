package com.lando.notesappkotlin.ui.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lando.notesappkotlin.data.model.note.UpdateNote
import com.lando.notesappkotlin.data.remote.source.note.NoteRemoteDataSource
import com.lando.notesappkotlin.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getNoteInfo()
        clickUpdateBtn()
    }

    /*Sets the note info on the text boxes when selecting one*/
    private fun getNoteInfo()
    {
        val noteTitle = intent.getStringExtra("note_title")
        val noteContent = intent.getStringExtra("note_content")
        binding.etUpdateTitle.setText(noteTitle)
        binding.etUpdateContent.setText(noteContent)
    }

    private fun clickUpdateBtn()
    {
        binding.btnUpdate.setOnClickListener {
            if (binding.etUpdateTitle.text.isEmpty() or binding.etUpdateContent.text.isEmpty())
            {
                Toast.makeText(this, "Please fill the boxes", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val noteAPI = NoteRemoteDataSource()
            val userID = intent.getIntExtra("user_id", 0)
            val noteID = intent.getIntExtra("update_note_id", 0)

            val updatedData = UpdateNote(
                userID,
                noteID,
                binding.etUpdateTitle.text.toString(),
                binding.etUpdateContent.text.toString()
            )

            noteAPI.updateNote(updatedData)
            val intent = Intent(this, NotesActivity::class.java)
            intent.putExtra("user_id", userID)
            startActivity(intent)
        }
    }
}