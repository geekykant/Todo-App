package com.paavam.todoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paavam.todoapp.R
import com.paavam.todoapp.db.Note
import com.paavam.todoapp.interfaces.NotesListener

public class NotesAdapter(private val allNotesList: List<Note>, private val listener: NotesListener) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title_item)
        val desc = itemView.findViewById<TextView>(R.id.desc_item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_item_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return allNotesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = allNotesList[position]

        holder.title.text = note.title
        holder.desc.text = note.description

        holder.itemView.setOnClickListener { listener.onClickedItem(note) }
    }

}