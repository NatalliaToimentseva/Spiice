package com.example.spiice.ui.notesListScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.spiice.databinding.ScheduledNotesListItemBinding
import com.example.spiice.databinding.SimpleNotesListItemBinding
import com.example.spiice.entities.noteEntity.Note
import com.example.spiice.entities.noteEntity.NoteEntity
import com.example.spiice.entities.noteEntity.ScheduledNoteEntity
import java.time.LocalDate

class NotesAdapter(
    private val onClick: (note: Note) -> Unit
) : ListAdapter<Note, ViewHolder>(NoteDiffUtil()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is NoteEntity -> ViewHolderType.SIMPLE.id
            is ScheduledNoteEntity -> ViewHolderType.SCHEDULED.id
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
            is NoteEntity -> {
                (holder as? SimpleNoteViewHolder)?.bind(item, onClick)
            }

            is ScheduledNoteEntity -> {
                (holder as? ScheduledNoteViewHolder)?.bind(item, localDate, onClick)
            }
        }
    }
}