package com.example.spiice.notes.adaptor

import androidx.recyclerview.widget.DiffUtil
import com.example.spiice.entities.NoteEntity

class NoteDiffUtil : DiffUtil.ItemCallback<NoteEntity>() {

    override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
        return oldItem == newItem
    }
}