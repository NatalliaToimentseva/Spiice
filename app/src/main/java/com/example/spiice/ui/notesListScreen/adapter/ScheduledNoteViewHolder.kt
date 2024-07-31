package com.example.spiice.ui.notesListScreen.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.spiice.R
import com.example.spiice.databinding.ScheduledNotesListItemBinding
import com.example.spiice.models.noteModel.ScheduledNote
import com.example.spiice.utils.convertDataFromLocalDataToString
import java.time.LocalDate

class ScheduledNoteViewHolder(
    private val binding: ScheduledNotesListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        note: ScheduledNote,
        localeData: LocalDate,
        fragmentId: Int,
        onClick: (note: ScheduledNote) -> Unit
    ) = binding.run {
        if (fragmentId == R.id.notes_list) {
            when {
                note.scheduledDate > localeData -> noteScheduledData.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.bg_future_note
                    )
                )

                note.scheduledDate == localeData -> noteScheduledData.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.bg_present_note
                    )
                )

                note.scheduledDate < localeData -> noteScheduledData.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.bg_past_note
                    )
                )
            }
        } else if (fragmentId == R.id.search_fragment) {
            scheduledNotesListItem.setBackgroundResource(R.drawable.bg_search_field)
        }
        noteTitle.text = note.title
        noteTitle.setOnClickListener {
            onClick(note)
        }
        noteAddedData.text = convertDataFromLocalDataToString(note.addedDate)
        noteScheduledData.text = convertDataFromLocalDataToString(note.scheduledDate)
        noteMessage.text = note.message
        noteMessage.setOnClickListener {
            val tv = noteMessage
            if (tv.maxLines == NOTE_MESSAGE_LINES) {
                tv.maxLines = Int.MAX_VALUE
            } else tv.maxLines = NOTE_MESSAGE_LINES
        }
    }
}