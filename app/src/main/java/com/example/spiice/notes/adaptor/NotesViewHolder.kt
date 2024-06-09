package com.example.spiice.notes.adaptor

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spiice.R
import com.example.spiice.entities.NoteEntity

class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val noteTitleTV = view.findViewById<TextView>(R.id.note_title)
    private val noteStartDataTV = view.findViewById<TextView>(R.id.note_start_data)
    private val noteMessageTV = view.findViewById<TextView>(R.id.note_message)

    fun bind(note: NoteEntity, onClick: (note: NoteEntity) -> Unit) {
        noteTitleTV.text = note.title
        noteTitleTV.setOnClickListener {
            onClick(note)
        }
        noteStartDataTV.text = note.startingData
        noteMessageTV.text = note.message
    }
}