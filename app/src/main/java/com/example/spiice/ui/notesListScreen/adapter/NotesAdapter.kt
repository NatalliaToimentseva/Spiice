package com.example.spiice.ui.notesListScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.spiice.databinding.ScheduledNotesListItemBinding
import com.example.spiice.databinding.SimpleNotesListItemBinding
import com.example.spiice.models.noteModel.Note
import com.example.spiice.models.noteModel.SimpleNote
import com.example.spiice.models.noteModel.ScheduledNote
import java.time.LocalDate

class NotesAdapter(
    private val fragmentId: Int,
    private val onClick: (note: Note) -> Unit
) : ListAdapter<Note, ViewHolder>(NoteDiffUtil()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is SimpleNote -> ViewHolderType.SIMPLE.id
            is ScheduledNote -> ViewHolderType.SCHEDULED.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ViewHolderType.SIMPLE.id -> SimpleNoteViewHolder(
                SimpleNotesListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            ViewHolderType.SCHEDULED.id -> ScheduledNoteViewHolder(
                ScheduledNotesListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> {
                val recyclerView = object : ViewHolder(FrameLayout(parent.context)) {}
                recyclerView
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val localDate = LocalDate.now()
        when (val item = getItem(position)) {
            is SimpleNote -> {
                (holder as? SimpleNoteViewHolder)?.bind(item, fragmentId, onClick)
            }

            is ScheduledNote -> {
                (holder as? ScheduledNoteViewHolder)?.bind(item, localDate, fragmentId, onClick)
            }
        }
    }
}