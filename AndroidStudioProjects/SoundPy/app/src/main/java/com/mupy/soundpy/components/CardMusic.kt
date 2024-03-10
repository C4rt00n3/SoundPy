package com.mupy.soundpy.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mupy.soundpy.R
import com.mupy.soundpy.database.Music
import com.mupy.soundpy.ui.theme.ColorWhite
import com.mupy.soundpy.ui.theme.SoundPyTheme
import com.mupy.soundpy.ui.theme.WhiteTransparent

@Composable
fun CardMusic(
    music: Music?, onClick: (Music) -> Unit, onRemove: (Music) -> Unit
) {
    val modifier = Modifier
        .size(185.dp)
        .padding(bottom = 16.dp)
        .clip(ShapeDefaults.Medium)
        .background(WhiteTransparent, ShapeDefaults.Medium)
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        modifier = Modifier
            .background(WhiteTransparent)
            .pointerInput(Unit) {
                detectTapGestures(onTap = { music?.let { onClick(it) } },
                    onLongPress = { music?.let { onRemove(it) } })
            }) {
        ImageComponent(
            music?.thumb ?: "",
            music?.bitImage,
            modifier,
            contentScale = ContentScale.Companion.FillBounds,
        )
        Text(
            text = music?.title ?: stringResource(id = R.string.carregando),
            fontWeight = FontWeight.W600,
            color = ColorWhite,
            fontSize = 20.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.widthIn(max = 250.dp)
        )

    }
    Spacer(modifier = Modifier.width(50.dp))
}

@Preview(showBackground = true)
@Composable
private fun CardMusicPreview() {
    SoundPyTheme {
        CardMusic(null, {}) {}
    }
}