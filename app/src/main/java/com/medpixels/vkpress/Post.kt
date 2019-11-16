package com.medpixels.vkpress

import java.util.*

class Post (
    val fullName: String,
    val createdAt: Date,
    val profilePic: String,
    val image: String? = "",
    val btnName: String? = "",
    val nbVu: Int,
    val nbComment: Int,
    val nbLike: Int
)