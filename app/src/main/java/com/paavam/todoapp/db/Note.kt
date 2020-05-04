package com.paavam.todoapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesData")
data class Note(

        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        var title: String = "",
        var description: String = "",
        var imagePath: String = "",
        var isTaskCompleted: Boolean = false

)