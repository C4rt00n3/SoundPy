package com.mupy.soundpy.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mupy.soundpy.ContextMain
import com.mupy.soundpy.database.MyPlaylists
import com.mupy.soundpy.database.PlaylistWithMusic
import com.mupy.soundpy.ui.theme.LineColor
import com.mupy.soundpy.ui.theme.TrackColor
import com.mupy.soundpy.ui.theme.WhiteTransparent
import com.mupy.soundpy.utils.SoundPy

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun Progress(
    viewModel: ContextMain
) {
    val soundPy: SoundPy? by viewModel.soundPy.observeAsState(null)
    val progress: Float by viewModel.progress.observeAsState(0f)

    Slider(
        value = progress, onValueChange = {
            viewModel.setProgress(it)
            val duration = soundPy?.player?.duration ?: 0
            val newPosition = (duration * it).toLong()

            if (duration > 0 && newPosition >= 0 && newPosition <= duration) {
                soundPy?.player?.seekTo(newPosition)
            }
        }, valueRange = 0f..1f, steps = 100, colors = SliderDefaults.colors(
            thumbColor = LineColor,
            activeTrackColor = LineColor,
            activeTickColor = Color.Transparent,
            inactiveTickColor = Color.Transparent,
            inactiveTrackColor = TrackColor
        ), modifier = Modifier
            .fillMaxWidth()
            .height(25.dp)
    )
}
