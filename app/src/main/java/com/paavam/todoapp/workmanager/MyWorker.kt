package com.paavam.todoapp.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.paavam.todoapp.NotesApp

class MyWorker(val context: Context, private val workerParameters: WorkerParameters) :  Worker(context, workerParameters) {

    override fun doWork(): Result {
        val notesApp = applicationContext as NotesApp
        notesApp.getNotesDb().notesDao().deleteNotes()
        return Result.success()
    }

}