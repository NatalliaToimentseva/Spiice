package com.example.spiice.ui.notesListScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spiice.R
import com.example.spiice.databinding.FragmentNotesListBinding
import com.example.spiice.models.noteModel.Note
import com.example.spiice.models.noteModel.SimpleNote
import com.example.spiice.models.noteModel.ScheduledNote
import com.example.spiice.repositoty.SharedPreferencesRepository
import com.example.spiice.ui.notesListScreen.adapter.NotesAdapter
import com.example.spiice.utils.makeToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotesListFragment : Fragment() {

    private var binding: FragmentNotesListBinding? = null
    private val viewModel: NotesListViewModel by viewModels()

    @Inject
    lateinit var sharedPreferencesRepository: SharedPreferencesRepository

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
        sharedPreferencesRepository.getEmail()?.let { viewModel.getNotesList(it) }
    }

    private fun setNoteListAdapter(notes: List<Note>) {
        binding?.notesListRecycleView?.run {
            if (adapter == null) {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = NotesAdapter (R.id.notes_list){ note ->
                    when (note) {
                        is SimpleNote -> makeToast(requireActivity(), note.title)
                        is ScheduledNote -> makeToast(requireActivity(), note.title)
                    }
                }
            }
            (adapter as? NotesAdapter)?.submitList(notes)
        }
    }
}