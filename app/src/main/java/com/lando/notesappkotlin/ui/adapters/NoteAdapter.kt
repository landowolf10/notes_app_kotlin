package com.lando.notesappkotlin.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lando.notesappkotlin.R
import com.lando.notesappkotlin.data.model.note.Note
import com.lando.notesappkotlin.data.remote.source.note.NoteRemoteDataSource
import com.lando.notesappkotlin.databinding.ActivityMainBinding
import com.lando.notesappkotlin.databinding.CardViewBinding
import com.lando.notesappkotlin.ui.views.UpdateNoteActivity

class NoteAdapter(note: ArrayList<Note>): RecyclerView.Adapter<NoteAdapter.ViewHolder>()
{
    private lateinit var binding: ActivityMainBinding

    var noteList = note

    fun setData(note: ArrayList<Note>)
    {
        this.noteList = note
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(
            R.layout.card_view,
            parent,
            false
        )

        binding = ActivityMainBinding.inflate(LayoutInflater.from(
            parent.context
        ),
            parent,
            false
        )

        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(noteList[position])
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        private val binding = CardViewBinding.bind(view)

        fun render(item: Note)
        {
            val noteTitle: TextView = itemView.findViewById(R.id.txtTitle)
            val noteContent: TextView = itemView.findViewById(R.id.txtContent)

            noteTitle.text = item.title
            noteContent.text = item.content

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, UpdateNoteActivity::class.java)
                intent.putExtra("user_id", item.userID)
                intent.putExtra("update_note_id", item.id)
                intent.putExtra("note_title", item.title)
                intent.putExtra("note_content", item.content)
                itemView.context.startActivity(intent)
            }

            binding.btnDelete.setOnClickListener {
                val noteAPI = NoteRemoteDataSource()
                val noteID: Int = item.id
                val itemPosition = adapterPosition

                noteList.removeAt(itemPosition)
                noteAPI.deleteNote(noteID)
                notifyItemRemoved(itemPosition)
            }
        }
    }
}