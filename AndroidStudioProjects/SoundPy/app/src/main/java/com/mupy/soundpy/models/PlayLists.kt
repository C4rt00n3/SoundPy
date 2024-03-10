package com.mupy.soundpy.models

import com.mupy.soundpy.database.Music

data class PlayLists(
    val id: Int,
    val link: String,
    val musics: MutableList<Music>
)