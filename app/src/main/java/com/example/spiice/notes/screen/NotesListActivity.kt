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
import androidx.recyclerview.widget.RecyclerView
import com.example.spiice.InMemoryNotesList
import com.example.spiice.R
import com.example.spiice.entities.NoteEntity
import com.example.spiice.notes.adaptor.NotesAdapter

const val KEY = "KEY"

class NotesListActivity : AppCompatActivity(), MenuProvider {
    private var newNote: NoteEntity? = null
    private var launcher: ActivityResultLauncher<Intent>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)

        setSupportActionBar(findViewById(R.id.notes_list_toolbar))
        addMenuProvider(this)
        setNoteListAdapter(InMemoryNotesList.getNotes())

        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                newNote = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    result.data?.getParcelableExtra(KEY, NoteEntity::class.java)
                } else {
                    @Suppress("DEPRECATION") result.data?.getParcelableExtra(KEY)
                }
                newNote?.let { InMemoryNotesList.setNotes(it) }
                setNoteListAdapter(InMemoryNotesList.getNotes())
            }
        }
    }

    private fun setNoteListAdapter(noteList: List<NoteEntity>) {
        findViewById<RecyclerView>(R.id.notes_list_recycleView).run {
            if (adapter == null) {
                layoutManager = LinearLayoutManager(this@NotesListActivity)
                adapter = NotesAdapter()
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