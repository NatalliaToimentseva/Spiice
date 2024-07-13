package com.example.spiice.ui.notesListScreen.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.spiice.R
import com.example.spiice.databinding.SimpleNotesListItemBinding
import com.example.spiice.models.noteModel.SimpleNote
import com.example.spiice.utils.convertDataFromLocalDataToString

const val NOTE_MESSAGE_LINES = 2

class SimpleNoteViewHolder(
    private val binding: SimpleNotesListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        note: SimpleNote,
        fragmentId: Int,
        onClick: (note: SimpleNote) -> Unit
    ) = binding.run {
        if (fragmentId == R.id.search_fragment) {
            simpleNotesListItem.setBackgroundResource(R.drawable.bg_search_field)
        }
        noteTitle.text = note.title
        noteTitle.setOnClickListener {
            onClick(note)
        }
        noteAddedData.text = convertDataFromLocalDataToString(note.addedData)
        noteMessage.text = note.message
        noteMessage.setOnClickListener {
            val tv = noteMessage
            if (tv.maxLines == NOTE_MESSAGE_LINES) {
                tv.maxLines = Int.MAX_VALUE
            } else tv.maxLines = NOTE_MESSAGE_LINES
        }
    }
}