package com.example.spiice.ui.notesListScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spiice.R
import com.example.spiice.databinding.FragmentNotesListBinding
import com.example.spiice.models.noteModel.Note
import com.example.spiice.models.noteModel.SimpleNote
import com.example.spiice.models.noteModel.ScheduledNote
import com.example.spiice.navigator.navigator
import com.example.spiice.repositoty.SharedPreferencesRepository
import com.example.spiice.ui.addNoteScreen.AddNoteFragment
import com.example.spiice.ui.logInScreen.LogInFragment
import com.example.spiice.ui.notesListScreen.adapter.NotesAdapter
import com.example.spiice.utils.makeToast

const val EMAIL = "email"

class NotesListFragment : Fragment() {

    private var binding: FragmentNotesListBinding? = null
    private val viewModel: NotesListViewModel by viewModels()

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
        viewModel.notesList.observe(viewLifecycleOwner) { list ->
            setNoteListAdapter(list)
        }
        arguments?.getString(EMAIL)?.let { viewModel.addEmail(it) }

        viewModel.getNotesList()

        binding?.notesListToolbar?.let { toolbar ->
            toolbar.setNavigationOnClickListener {
                SharedPreferencesRepository.clearUserData()
                navigator().cancelFragment()
                navigator().startFragment(LogInFragment())
            }
            toolbar.setOnMenuItemClickListener { item ->
                if (item.itemId == R.id.add_new_note) {
                    navigator().cancelFragment()
                    navigator().addFragment(AddNoteFragment())
                }
                return@setOnMenuItemClickListener true
            }
        }
    }

    private fun setNoteListAdapter(notes: List<Note>) {
        binding?.notesListRecycleView?.run {
            if (adapter == null) {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = NotesAdapter { note ->
                    when (note) {
                        is SimpleNote -> makeToast(requireActivity(), note.title)
                        is ScheduledNote -> makeToast(requireActivity(), note.title)
                    }
                }
            }
            (adapter as? NotesAdapter)?.submitList(notes)
        }
    }

    companion object {
        fun getFragment(email: String): NotesListFragment {
            return NotesListFragment().apply {
                arguments = bundleOf(EMAIL to email)
            }
        }
    }
}