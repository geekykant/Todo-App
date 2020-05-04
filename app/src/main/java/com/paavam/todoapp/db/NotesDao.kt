package com.paavam.todoapp.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update

interface NotesDao {

    @Query("SELECT * FROM notesData")
    fun getAllNotes(): List<Note>

    @Insert(onConflict = REPLACE)
    fun insertNote(note: Note)

    @Update
    fun updateNotes(note: Note)

    @Delete
    fun deleteNotes(note: Note)

}