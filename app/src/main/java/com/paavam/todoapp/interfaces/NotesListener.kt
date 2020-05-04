package com.paavam.todoapp.interfaces

import com.paavam.todoapp.db.Note

interface NotesListener {
    fun onClickedItem(note: Note)
    fun onTaskCompleted(note: Note)
}