package com.example.spiice.notes.screen

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spiice.InMemoryNotesList
import com.example.spiice.R
import com.example.spiice.databinding.ActivityNotesListBinding
import com.example.spiice.entities.Note
import com.example.spiice.entities.NoteEntity
import com.example.spiice.entities.ScheduledNoteEntity
import com.example.spiice.notes.adaptor.NotesAdapter
import com.example.spiice.utils.makeToast

const val KEY = "KEY"
const val DEPRECATION = "DEPRECATION"

class NotesListActivity : AppCompatActivity(), MenuProvider {

    private var binding: ActivityNotesListBinding? = null
    private var launcher: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNotesListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        binding?.run { setSupportActionBar(notesListToolbar) }
        addMenuProvider(this)
        setNoteListAdapter(InMemoryNotesList.getNotes())

        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    result.data?.getParcelableExtra(KEY, Note::class.java)
                } else {
                    @Suppress(DEPRECATION) result.data?.getParcelableExtra(KEY)
                }?.let { InMemoryNotesList.setNotes(it) }
                setNoteListAdapter(InMemoryNotesList.getNotes())
            }
        }
    }

    private fun setNoteListAdapter(noteList: List<Note>) {
        binding?.notesListRecycleView?.run {
            if (adapter == null) {
                layoutManager = LinearLayoutManager(this@NotesListActivity)
                adapter = NotesAdapter { note ->
                    when(note) {
                        is NoteEntity -> makeToast(applicationContext, note.title)
                        is ScheduledNoteEntity -> makeToast(applicationContext, note.title)
                    }
                }
            }
            (adapter as? NotesAdapter)?.submitList(noteList)
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.notes_list_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> finish()
            R.id.add_new_note -> launcher?.launch(Intent(this, AddNoteActivity::class.java))
        }
        return true
    }
}