package com.medpixels.vkpress

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_card.view.*
import kotlinx.android.synthetic.main.product_card.view.*


class ProductViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val image = view.image_product
    val desc = view.desc_product
    val price = view.price
    val cart = view.cart
    val sold = view.nbsold
}
