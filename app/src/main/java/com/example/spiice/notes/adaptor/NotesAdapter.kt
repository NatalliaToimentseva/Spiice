package com.example.spiice.notes.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
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
        holder.itemView.setOnClickListener {
            val tv = holder.itemView.findViewById<TextView>(R.id.note_message)
            if (tv.maxLines == 2) {
                tv.maxLines = Int.MAX_VALUE
            } else tv.maxLines = 2
        }
    }
}