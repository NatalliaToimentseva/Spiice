package com.example.spiice.notes.adaptor

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.spiice.databinding.NotesListItemBinding
import com.example.spiice.entities.NoteEntity
import com.example.spiice.utils.convertDataFromLocalDataToString

class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = NotesListItemBinding.bind(view)

    fun bind(note: NoteEntity, onClick: (note: NoteEntity) -> Unit) = binding.run {
        noteTitle.text = note.title
        noteTitle.setOnClickListener {
            onClick(note)
        }
        noteStartData.text = convertDataFromLocalDataToString(note.startingData)
        noteMessage.text = note.message
        noteMessage.setOnClickListener {
            val tv = noteMessage
            if (tv.maxLines == 2) {
                tv.maxLines = Int.MAX_VALUE
            } else tv.maxLines = 2
        }
    }
}
