package com.medpixels.vkpress

import android.app.Activity
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.objects.FirebaseVisionObjectDetectorOptions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.aliexpress_layout.*
import kotlinx.android.synthetic.main.aliexpress_layout.view.*
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit
import javax.sql.DataSource
import kotlin.collections.ArrayList
import kotlin.math.abs


class PostAdapter (private var items : ArrayList<Post>, private val context: Activity) :
    RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(context).inflate(R.layout.post_card, parent, false))
    }

    override fun getItemCount(): Int { return items.size }

    fun change (newItems : ArrayList<Post>)  {items.clear() ; items.addAll(newItems)}

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder?.name?.text = items[position].fullName
        holder?.time?.text = ThreadLocalRandom.current().nextInt(1, 10).toString() + " minutes ago"
        holder?.bottomName?.text = items[position].btnName
        holder?.nbLikes?.text = items[position].nbLike.toString()
        holder?.nbComment?.text = items[position].nbComment.toString()
        holder?.nbVu?.text = items[position].nbVu.toString()
        holder?.profilePic.clipToOutline = true
        Glide.with(context).load( items[position].image).centerInside().listener(object : RequestListener<Drawable> {
            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: com.bumptech.glide.load.DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                Log.e("Nice" , "Nice")

                //detect
                // Multiple object detection in static images
                val options = FirebaseVisionObjectDetectorOptions.Builder()
                    .setDetectorMode(FirebaseVisionObjectDetectorOptions.SINGLE_IMAGE_MODE)
                    .enableMultipleObjects()
                    .enableClassification()  // Optional
                    .build()

                val objectDetector = FirebaseVision.getInstance().getOnDeviceObjectDetector(options)

                val imagee = FirebaseVisionImage.fromBitmap(resource!!.toBitmap())

                objectDetector.processImage(imagee)
                    .addOnSuccessListener { detectedObjects ->
                        // Task completed successfully
                        for (obj in detectedObjects) {
                            val id = obj.trackingId       // A number that identifies the object across images
                            val bounds = obj.boundingBox  // The object's position in the image
                            // If classification was enabled:
                            val category = obj.classificationCategory
                            val confidence = obj.classificationConfidence
                            Log.e("Tags" , bounds.toString())
                            val cro = (holder?.image as ImageView).drawable

                            val btn = Button(context)
                            val params = ConstraintLayout.LayoutParams(30.px, 30.px)
                            params.topToTop = holder?.constraint.id
                            params.startToStart = holder?.constraint.id
                            params.marginStart = bounds.exactCenterX().toInt() + 20.px
                            params.topMargin = bounds.exactCenterY().toInt() + 20.px
                            btn.id = id.hashCode()
                            btn.setBackgroundResource(R.drawable.clickable)
                            holder.constraint.addView(btn,params)
                            btn.setOnClickListener {
                                val mBottomSheetDialog = BottomSheetDialog(context)
                                val sheetView = context.layoutInflater.inflate(R.layout.aliexpress_layout, null)
                                mBottomSheetDialog.setContentView(sheetView)
                                mBottomSheetDialog.show()
                                sheetView.rootView.setBackgroundResource(android.R.color.transparent)
                                //listView
                                sheetView.products.layoutManager = LinearLayoutManager(context , LinearLayoutManager.HORIZONTAL , false)
                                val allProducts = ArrayList<Product>()
                                allProducts.add(Product("Adidas f chbab" , "https://assets.adidas.com/images/w_600,f_auto,q_auto:sensitive,fl_lossy/69721f2e7c934d909168a80e00818569_9366/Chaussure_Stan_Smith_Blanc_M20324_01_standard.jpg" , "50", "US" , 111))
                                allProducts.add(Product("Adidas f chbab" , "https://assets.adidas.com/images/w_600,f_auto,q_auto:sensitive,fl_lossy/69721f2e7c934d909168a80e00818569_9366/Chaussure_Stan_Smith_Blanc_M20324_01_standard.jpg" , "50", "US" , 111))
                                allProducts.add(Product("Adidas f chbab" , "https://assets.adidas.com/images/w_600,f_auto,q_auto:sensitive,fl_lossy/69721f2e7c934d909168a80e00818569_9366/Chaussure_Stan_Smith_Blanc_M20324_01_standard.jpg" , "50", "US" , 111))
                                allProducts.add(Product("Adidas f chbab" , "https://assets.adidas.com/images/w_600,f_auto,q_auto:sensitive,fl_lossy/69721f2e7c934d909168a80e00818569_9366/Chaussure_Stan_Smith_Blanc_M20324_01_standard.jpg" , "50", "US" , 111))
                                val adapter = ProductAdapter(allProducts, context)
                                sheetView.products.adapter = adapter
                                adapter.notifyDataSetChanged()
                                mBottomSheetDialog.setCanceledOnTouchOutside(true)
                                mBottomSheetDialog.setCancelable(true)
                            }
                        }
                    }
                    .addOnFailureListener { e ->
                        // Task failed with an exception
                        // ...
                        Log.e("Error" , "Error everywhere")
                    }

                return false
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                Log.e("Error" , "Error everywhere")
                return false
            }
        }).into(holder?.image)
        Glide.with(context).load( items[position].profilePic).centerCrop().into(holder?.profilePic)


        /*

           */


    }

    val Int.dp: Int
        get() = (this / Resources.getSystem().displayMetrics.density).toInt()

    val Int.px: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()
}