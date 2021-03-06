package com.example.wikipediaapp.ui

import com.example.wikipediaapp.model.Pages
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wikipediaapp.R
import com.example.wikipediaapp.data.WikiPage

class RecyclerAdapter(private val mList: List<Pages>, private val listener:SearchItemClicked) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val thumbnail: ImageView = itemView.findViewById(R.id.iv_rv)
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val desc: TextView = itemView.findViewById(R.id.tv_desciption)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv, parent, false)
        val viewHolder=ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            listener.itemClicked(WikiPage(mList[viewHolder.adapterPosition].pageid,mList[viewHolder.adapterPosition].title,mList[viewHolder.adapterPosition].terms.description[0].toString()))
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pages = mList[position]

        // sets the image to the imageview from our itemHolder class
        //holder.thumbnail.setImageResource(pages.pageimage)
        if (mList[position].thumbnail?.source != null)
            Glide.with(holder.itemView.context).load(mList[position].thumbnail.source)
                .into(holder.thumbnail)

        // sets the text to the textview from our itemHolder
        if (pages.title != null)
            holder.title.text = pages.title
        if (pages.terms != null)
            holder.desc.text = pages.terms.description[0]

    }

    override fun getItemCount(): Int {
        return mList.size
    }
}
interface SearchItemClicked{
    fun itemClicked(item:WikiPage)
}