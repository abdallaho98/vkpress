package com.medpixels.vkpress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.drawable.updateBounds
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.objects.FirebaseVisionObjectDetectorOptions
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.Bitmap
import android.R.attr.left
import android.R.attr.right
import android.graphics.Matrix
import android.graphics.Rect
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlin.math.abs


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Multiple object detection in static images
        val options = FirebaseVisionObjectDetectorOptions.Builder()
            .setDetectorMode(FirebaseVisionObjectDetectorOptions.SINGLE_IMAGE_MODE)
            .enableMultipleObjects()
            .enableClassification()  // Optional
            .build()

        val objectDetector = FirebaseVision.getInstance().getOnDeviceObjectDetector(options)

        val imagee = FirebaseVisionImage.fromBitmap((image as ImageView).drawable.toBitmap())

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
                    val cro = (image as ImageView).drawable
                    crop.setImageBitmap(Bitmap.createBitmap(cro.toBitmap(),bounds.left,bounds.top,(abs(bounds.right - bounds.left) * 1.0).toInt()  ,
                        (abs(bounds.bottom - bounds.top) * 1.0).toInt()))
                }
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                // ...
                Log.e("Error" , "Error everywhere")
            }
    }



}
