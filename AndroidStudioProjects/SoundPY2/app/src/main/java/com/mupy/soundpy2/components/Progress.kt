package com.mupy.soundpy2.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mupy.soundpy2.ContextMain
import com.mupy.soundpy2.ui.theme.LineColor
import com.mupy.soundpy2.ui.theme.TrackColor
import com.mupy.soundpy2.utils.Play

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun Progress(viewModel: ContextMain) {
    val player: Play? by viewModel.player.observeAsState(Play())
    val progress: Float by viewModel.progress.observeAsState(0f)

    Slider(
        value = progress,
        onValueChange = {
            viewModel.setProgress(it)
            player?.seekTo((player?.duration?.times(progress))?.toInt() ?: 0)
        },
        valueRange = 0f..1f,
        steps = 100,
        colors = SliderDefaults.colors(
            thumbColor = Color.Transparent,
            activeTrackColor = LineColor,
            activeTickColor = Color.Transparent,
            inactiveTrackColor = TrackColor,
        ), modifier = Modifier
            .fillMaxWidth()
            .height(15.dp)
            .background(Color.Transparent)
    )
}