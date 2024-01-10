package com.mupy.music.models


data class PlayLists(
    val id: Int,
    val link: String,
    val musics: MutableList<Music>
)