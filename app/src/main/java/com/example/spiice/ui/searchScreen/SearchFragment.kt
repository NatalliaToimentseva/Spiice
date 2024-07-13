package com.example.spiice.ui.searchScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spiice.R
import com.example.spiice.databinding.FragmentSearchBinding
import com.example.spiice.models.noteModel.Note
import com.example.spiice.models.noteModel.ScheduledNote
import com.example.spiice.models.noteModel.SimpleNote
import com.example.spiice.repositoty.SharedPreferencesRepository
import com.example.spiice.ui.notesListScreen.adapter.NotesAdapter
import com.example.spiice.utils.makeToast
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    var binding: FragmentSearchBinding? = null
    private val viewModel: SearchViewModel by viewModels()

    @Inject
    lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.searchNoteList.observe(viewLifecycleOwner) { notesList ->
            setNoteListAdapter(notesList)
        }

        binding?.searchView?.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding?.searchView?.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { text ->
                    sharedPreferencesRepository.getEmail()?.let { email ->
                        viewModel.getSearchNotes(
                            text.lowercase(Locale.getDefault()),
                            email
                        )
                    }
                }
                return false
            }
        })
    }

    private fun setNoteListAdapter(notes: List<Note>) {
        binding?.searchRecycleView?.run {
            if (adapter == null) {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = NotesAdapter (R.id.search_fragment){ note ->
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