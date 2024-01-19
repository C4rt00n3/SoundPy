package com.mupy.soundpy2.models

import android.media.MediaPlayer
import java.io.File

data class ContextUtils(
    val pause: Boolean,
    val mediaPlayer: MediaPlayer?,
    val music: Music,
    val musics: MutableList<File>,
    val playListData: PlayListData,
    val getFiles: () -> Unit,
    val setPause: (Boolean) -> Unit,
    val setMediaPlayer: (MediaPlayer?)-> Unit,
    val setMusics:(MutableList<File>) -> Unit,
    val setMusic: (Music) -> Unit,
    val setPlaylist:(PlayListData)-> Unit
)