package com.example.spiice.ui.notesListScreen.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.spiice.R
import com.example.spiice.databinding.ScheduledNotesListItemBinding
import com.example.spiice.entities.ScheduledNoteEntity
import com.example.spiice.utils.convertDataFromLocalDataToString
import java.time.LocalDate

class ScheduledNoteViewHolder(
    private val binding: ScheduledNotesListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        note: ScheduledNoteEntity,
        localeData: LocalDate,
        onClick: (note: ScheduledNoteEntity) -> Unit
    ) = binding.run {
        when {
            note.scheduledData > localeData -> scheduledNotesListItem.setBackgroundResource(R.drawable.bg_future_note)
            note.scheduledData == localeData -> scheduledNotesListItem.setBackgroundResource(R.drawable.bg_present_note)
            note.scheduledData < localeData -> scheduledNotesListItem.setBackgroundResource(R.drawable.bg_past_note)
        }
        noteTitle.text = note.title
        noteTitle.setOnClickListener {
            onClick(note)
        }
        noteAddedData.text = convertDataFromLocalDataToString(note.addedData)
        noteScheduledData.text = convertDataFromLocalDataToString(note.scheduledData)
        noteMessage.text = note.message
        noteMessage.setOnClickListener {
            val tv = noteMessage
            if (tv.maxLines == 2) {
                tv.maxLines = Int.MAX_VALUE
            } else tv.maxLines = 2
        }
    }
}