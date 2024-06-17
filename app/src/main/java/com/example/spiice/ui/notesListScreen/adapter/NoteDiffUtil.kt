package com.example.spiice.ui.notesListScreen.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.spiice.entities.Note

class NoteDiffUtil : DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}