package com.mupy.music.models

data class Music(
    val id: Long? = null,
    val author: String,
    val thumb: String,
    val title: String,
    val url: String
)