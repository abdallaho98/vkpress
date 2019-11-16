package com.esi.pharmacie.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medpixels.vkpress.Post
import com.medpixels.vkpress.R


class PostAdapter (var items : ArrayList<Post>, val context: Activity) :
    RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(context).inflate(R.layout.post_card, parent, false))
    }

    override fun getItemCount(): Int { return items.size }

    fun change (newItems : ArrayList<Post>)  {items.clear() ; items.addAll(newItems)}

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder?.name?.text = items[position]
        holder?.status?.text = items[position]


    }
}