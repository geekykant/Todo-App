package com.paavam.todoapp.db

import androidx.room.*

@Dao
interface NotesDao {
    @Query("SELECT * FROM notesData")
    fun getAllNotes(): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Update
    fun updateNotes(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM notesData WHERE isTaskCompleted")
    fun deleteNotes()
}