package com.example.spiice.notes.adaptor

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spiice.R
import com.example.spiice.entities.NoteEntity

class NotesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(note: NoteEntity) {
        view.findViewById<TextView>(R.id.note_title).text = note.title
        view.findViewById<TextView>(R.id.note_start_data).text = note.startingData
        view.findViewById<TextView>(R.id.note_message).text = note.message
    }
}