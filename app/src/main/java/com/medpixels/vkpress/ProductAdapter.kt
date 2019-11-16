package com.medpixels.vkpress

import android.app.Activity
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Transition
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomViewTarget
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.objects.FirebaseVisionObjectDetectorOptions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.aliexpress_layout.view.*
import kotlinx.android.synthetic.main.product_bought.view.*
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit
import javax.sql.DataSource
import kotlin.collections.ArrayList
import kotlin.math.abs


class ProductAdapter (private var items : ArrayList<Product>, private val context: Activity) :
    RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.product_card, parent, false))
    }

    override fun getItemCount(): Int { return items.size }

    fun change (newItems : ArrayList<Product>)  {items.clear() ; items.addAll(newItems)}

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder?.desc?.text = items[position].desc
        holder?.price?.text = items[position].currency + " " + items[position].price
        holder?.sold?.text = items[position].nbSold.toString() + " sold"
        Glide.with(context)
            .load(items[position].image)
            .into(holder?.image)
        holder.itemView.setOnClickListener {
            (context as MainActivity).openAliExpress(items[position])
        }
    }
}