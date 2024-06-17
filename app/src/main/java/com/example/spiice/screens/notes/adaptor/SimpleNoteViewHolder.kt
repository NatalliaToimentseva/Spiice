package com.example.spiice.screens.notes.adaptor

import androidx.recyclerview.widget.RecyclerView
import com.example.spiice.databinding.SimpleNotesListItemBinding
import com.example.spiice.entities.NoteEntity
import com.example.spiice.utils.convertDataFromLocalDataToString

class SimpleNoteViewHolder(
    private val binding: SimpleNotesListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        note: NoteEntity,
        onClick: (note: NoteEntity) -> Unit
    ) = binding.run {
        noteTitle.text = note.title
        noteTitle.setOnClickListener {
            onClick(note)
        }
        noteAddedData.text = convertDataFromLocalDataToString(note.addedData)
        noteMessage.text = note.message
        noteMessage.setOnClickListener {
            val tv = noteMessage
            if (tv.maxLines == 2) {
                tv.maxLines = Int.MAX_VALUE
            } else tv.maxLines = 2
        }
    }
}
