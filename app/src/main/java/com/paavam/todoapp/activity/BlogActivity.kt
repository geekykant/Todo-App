package com.paavam.todoapp.activity

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.paavam.todoapp.R
import com.paavam.todoapp.adapter.BlogAdapter
import com.paavam.todoapp.model.Data
import com.paavam.todoapp.model.JsonResponse


class BlogActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.blog_layout)

        supportActionBar?.title = "Notes Blog"
        progressBar = findViewById(R.id.progressBar);

        getBlogs()
    }

    fun setupRecyclerView(data: List<Data>) {
        recyclerView = findViewById(R.id.blog_recycler)
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = linearLayoutManager

        recyclerView.adapter = BlogAdapter(data)
        (recyclerView.adapter as BlogAdapter).notifyDataSetChanged()

        hideProgressBar()
    }

    private fun getBlogs() {
        AndroidNetworking.get("http://www.mocky.io/v2/5926ce9d11000096006ccb30")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(JsonResponse::class.java, object : ParsedRequestListener<JsonResponse> {
                    override fun onResponse(response: JsonResponse) {
                        setupRecyclerView(response.data)
                    }
                    override fun onError(anError: ANError?) {
                        Log.d("BlogActivity", anError?.localizedMessage!!)
                        hideProgressBar()
                    }
                })
    }

    fun hideProgressBar(){
        progressBar.visibility = ProgressBar.GONE
    }
}
