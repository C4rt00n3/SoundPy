package com.mupy.soundpy2.models

import android.graphics.Bitmap

data class Music(
    val id: Long? = null,
    val author: String,
    val thumb: String,
    val title: String,
    val url: String,
    val name: String?,
    val bitImage: Bitmap?
)