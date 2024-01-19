package com.mupy.soundpy2.models

import com.mupy.soundpy2.models.Music


data class PlayLists(
    val id: Int,
    val link: String,
    val musics: MutableList<Music>
)