package com.paavam.todoapp.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.paavam.todoapp.AppConstants
import com.paavam.todoapp.R
import com.paavam.todoapp.adapter.NotesAdapter
import com.paavam.todoapp.interfaces.NotesListener
import com.paavam.todoapp.model.Note
import java.util.*

class NotesActivity : AppCompatActivity(), NotesListener {

    private lateinit var fab: FloatingActionButton
    private val all_notes_list: MutableList<Note> = ArrayList()
    private var adapter: NotesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notes_layout)
        val fullName = intent.getStringExtra(AppConstants.FULL_NAME)
        supportActionBar!!.title = fullName
        init()
    }

    private fun init() {
        fab = findViewById(R.id.fabAddNote)
        fab.setOnClickListener { setupDialogBox() }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NotesAdapter(all_notes_list)
        adapter!!.setNotesListener(this)
        recyclerView.adapter = adapter
    }

    private fun setupDialogBox() {
        val view = LayoutInflater.from(this).inflate(R.layout.add_note_dialog, null)
        val title = view.findViewById<EditText>(R.id.title)
        val desc = view.findViewById<EditText>(R.id.description)
        val add_note_btn = view.findViewById<Button>(R.id.add_submit)
        val alertDialog = AlertDialog.Builder(this)
                .setView(view)
                .create()
        alertDialog.show()
        add_note_btn.setOnClickListener {
            val note = Note(title.text.toString(), desc.text.toString())
            all_notes_list.add(note)
            adapter!!.notifyDataSetChanged()
            alertDialog.dismiss()
        }
    }

    override fun onClickedItem(note: Note) {}
}