package com.example.wikipediaapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wikipediaapp.R
import com.example.wikipediaapp.data.WikiPage

class OfflineRecyclerAdapter(private val mList: List<WikiPage>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv, parent, false)
        val viewHolder = RecyclerAdapter.ViewHolder(view)

        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val pages = mList[position]
        // sets the text to the textview from our itemHolder
        if (pages.title != null)
            holder.title.text = pages.title
        if (pages.description != null)
            holder.desc.text = pages.description

    }

    override fun getItemCount(): Int {
        return mList.size
    }
}