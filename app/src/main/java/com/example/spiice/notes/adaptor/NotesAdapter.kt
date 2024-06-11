package com.example.spiice.notes.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.spiice.R
import com.example.spiice.entities.NoteEntity

class NotesAdapter(
    private val onClick: (note: NoteEntity) -> Unit
) : ListAdapter<NoteEntity, NotesViewHolder>(NoteDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.notes_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        getItem(position).run {
            holder.bind(this, onClick)
        }
    }
}