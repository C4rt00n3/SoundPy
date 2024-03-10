package com.mupy.soundpy.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

data class OptionUse(
    val onClick: () -> Unit,
    val text: String,
    val contentDescription: String,
    val paiter: Painter,
    val expand: (@Composable () -> Unit)?
) {}
