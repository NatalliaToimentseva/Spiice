package com.example.spiice.notes.adaptor

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spiice.R
import com.example.spiice.entities.NoteEntity
import com.example.spiice.utils.convertDataFromLocalDataToString

class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val noteTitleTV = view.findViewById<TextView>(R.id.note_title)
    private val noteStartDataTV = view.findViewById<TextView>(R.id.note_start_data)
    private val noteMessageTV = view.findViewById<TextView>(R.id.note_message)

    fun bind(note: NoteEntity, onClick: (note: NoteEntity) -> Unit) {
        noteTitleTV.text = note.title
        noteTitleTV.setOnClickListener {
            onClick(note)
        }
        noteStartDataTV.text = convertDataFromLocalDataToString(note.startingData)
        noteMessageTV.text = note.message
        noteMessageTV.setOnClickListener {
            val tv = this.itemView.findViewById<TextView>(R.id.note_message)
            if (tv.maxLines == 2) {
                tv.maxLines = Int.MAX_VALUE
            } else tv.maxLines = 2
        }
    }
}
