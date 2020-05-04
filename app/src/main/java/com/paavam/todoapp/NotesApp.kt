package com.paavam.todoapp

import android.app.Application
import com.paavam.todoapp.db.NoteDatabase

class NotesApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    fun getNotesDb(): NoteDatabase {
        return NoteDatabase.getInstance(this)
    }

}