package com.example.spiice.screens.account.notes.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spiice.InMemoryNotesList
import com.example.spiice.R
import com.example.spiice.databinding.FragmentNotesListBinding
import com.example.spiice.entities.NoteEntity
import com.example.spiice.entities.ScheduledNoteEntity
import com.example.spiice.entities.Subscriber
import com.example.spiice.navigator
import com.example.spiice.screens.account.notes.adaptor.NotesAdapter
import com.example.spiice.utils.makeToast

class NotesListFragment : Fragment(), Subscriber {

    private var binding: FragmentNotesListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setNoteListAdapter()
        InMemoryNotesList.addSubscriber(this)

        binding?.notesListToolbar?.let { toolbar ->
            toolbar.setNavigationOnClickListener {
                navigator().goBack()
            }
            toolbar.setOnMenuItemClickListener { item ->
                if (item.itemId == R.id.add_new_note) {
                    navigator().addFragment(AddNoteFragment())
                }
                return@setOnMenuItemClickListener true
            }
        }
    }

    override fun update() {
        setNoteListAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        InMemoryNotesList.removeSubscriber(this)
    }

    private fun setNoteListAdapter() {
        binding?.notesListRecycleView?.run {
            if (adapter == null) {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = NotesAdapter { note ->
                    when (note) {
                        is NoteEntity -> makeToast(requireActivity(), note.title)
                        is ScheduledNoteEntity -> makeToast(requireActivity(), note.title)
                    }
                }
            }
            (adapter as? NotesAdapter)?.submitList(InMemoryNotesList.getNotes())
        }
    }
}