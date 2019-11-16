package com.medpixels.vkpress

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_card.view.*


class PostViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val name = view.fullname
    val time = view.time
    val image = view.image
    val profilePic = view.profile_image
    val bottomName = view.btnname
    val nbLikes = view.nblikes
    val nbComment = view.nbcomment
    val nbVu = view.nbseen
    val more = view.dots
    val like = view.like
    val comment = view.comment
    var share = view.share
    val constraint = view.constraint
}
