package com.paavam.todoapp.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.paavam.todoapp.AppConstants
import com.paavam.todoapp.NotesApp
import com.paavam.todoapp.R
import com.paavam.todoapp.adapter.NotesAdapter
import com.paavam.todoapp.db.Note
import com.paavam.todoapp.db.NoteDatabase
import com.paavam.todoapp.interfaces.NotesListener
import com.paavam.todoapp.util.SharedPrefUtils
import java.util.*

class NotesActivity : AppCompatActivity() {

    private lateinit var fabAddNote: FloatingActionButton
    private var allNotesList = ArrayList<Note>()
    private lateinit var adapter: NotesAdapter

    private lateinit var notesDb: NoteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notes_layout)

        val fullName = SharedPrefUtils.getString(this, AppConstants.FULL_NAME)
        supportActionBar?.title = fullName

        setupDb()
        init()
    }

    private fun init() {
        fabAddNote = findViewById(R.id.fabAddNote)
        fabAddNote.setOnClickListener { setupDialogBox() }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val listener = object : NotesListener {
            override fun onClickedItem(note: Note) {

            }

            override fun onTaskCompleted(note: Note) {
            }

        }

        adapter = NotesAdapter(allNotesList, listener)
        recyclerView.adapter = adapter
    }

    private fun setupDb() {
        val app = applicationContext as NotesApp
        notesDb = app.getNotesDb()

        allNotesList = notesDb.notesDao().getAllNotes() as ArrayList<Note>
    }

    private fun setupDialogBox() {
        val view = LayoutInflater.from(this).inflate(R.layout.add_note_dialog, null)
        val title = view.findViewById<EditText>(R.id.title)
        val desc = view.findViewById<EditText>(R.id.description)
        val addNoteBtn = view.findViewById<Button>(R.id.add_submit)

        val alertDialog = MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
                .setView(view)
                .create()
        alertDialog.show()

        addNoteBtn.setOnClickListener {
            val note = Note(title = title.text.toString(), description = desc.text.toString())
            notesDb.notesDao().insert(note)

            adapter.notifyDataSetChanged()
            alertDialog.dismiss()
        }
    }

}