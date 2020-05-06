package com.paavam.todoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.paavam.todoapp.R
import com.paavam.todoapp.model.Data

class BlogAdapter(val list: List<Data>) : RecyclerView.Adapter<BlogAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.blog_item_row, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title_item)
        val desc: TextView = itemView.findViewById(R.id.desc_item)
        val image: ImageView = itemView.findViewById(R.id.image_item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]

        holder.title.text = data.title
        holder.desc.text = data.description

        Glide.with(holder.itemView.context).load(data.img_url).into(holder.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}