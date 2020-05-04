package com.paavam.todoapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {
        private lateinit var INSTANCE: NoteDatabase

        fun getInstance(context: Context): NoteDatabase {
            synchronized(NoteDatabase::class) {
                INSTANCE = Room.databaseBuilder(context, NoteDatabase::class.java, "my_notes.db")
                        .allowMainThreadQueries()
                        .build()
            }

            return INSTANCE
        }
    }

}