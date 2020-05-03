package com.paavam.todoapp.interfaces

import com.paavam.todoapp.model.Note

interface NotesListener {
    fun onClickedItem(note: Note)
}