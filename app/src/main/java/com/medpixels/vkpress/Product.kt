package com.medpixels.vkpress

import java.io.Serializable
import java.util.*

class Product  (
    val desc: String,
    val image: String,
    val price: String,
    val currency: String? = "US",
    val nbSold: Int
) : Serializable