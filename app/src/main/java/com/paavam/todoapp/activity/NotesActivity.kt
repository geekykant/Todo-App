package com.paavam.todoapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.paavam.todoapp.AppConstants
import com.paavam.todoapp.NotesApp
import com.paavam.todoapp.R
import com.paavam.todoapp.adapter.NotesAdapter
import com.paavam.todoapp.db.Note
import com.paavam.todoapp.db.NoteDatabase
import com.paavam.todoapp.interfaces.NotesListener
import com.paavam.todoapp.util.SharedPrefUtils
import com.paavam.todoapp.workmanager.MyWorker
import kotlinx.android.synthetic.main.notes_layout.*
import java.util.*
import java.util.concurrent.TimeUnit

class NotesActivity : AppCompatActivity() {

    private lateinit var fabAddNote: FloatingActionButton
    private var allNotesList = ArrayList<Note>()
    private lateinit var notesDb: NoteDatabase

    companion object {
        private const val ADD_NOTE_CODE = 140
    }

    private fun setupWorkManager() {
        val constraint = Constraints.Builder()
                .build()

        val request = PeriodicWorkRequest
                .Builder(MyWorker::class.java, 15, TimeUnit.MINUTES)
                .setConstraints(constraint)
                .build()
        WorkManager.getInstance(this).enqueue(request)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notes_layout)

        val fullName = SharedPrefUtils.getString(this, AppConstants.FULL_NAME)
        supportActionBar?.title = fullName

        setupDb()
        init()
        setupWorkManager()
    }

    private fun init() {
        fabAddNote = findViewById(R.id.fabAddNote)
        fabAddNote.setOnClickListener {
            intent = Intent(applicationContext, AddNoteActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_CODE)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val listener = object : NotesListener {
            override fun onClickedItem(note: Note) {
                //Nothing here
            }

            override fun onTaskCompleted(note: Note) {
                notesDb.notesDao().updateNotes(note)
            }

        }

        recyclerView.adapter = NotesAdapter(allNotesList, listener)
    }

    private fun setupDb() {
        val app = applicationContext as NotesApp
        notesDb = app.getNotesDb()

        allNotesList = notesDb.notesDao().getAllNotes() as ArrayList<Note>
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_NOTE_CODE) {
            allNotesList.clear()
            allNotesList.addAll(notesDb.notesDao().getAllNotes())
            recyclerView.adapter?.notifyItemInserted(allNotesList.size)
        }
    }
}