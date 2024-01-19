package com.mupy.soundpy2.utils

import android.media.MediaPlayer
import android.media.session.MediaSession

class Play : MediaPlayer() {
    fun timeUse(time: Int): String {
        val seconds = time / 1000f
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes.toInt(), remainingSeconds.toInt())
    }
}
