package com.mupy.soundpy.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mupy.soundpy.database.PlaylistWithMusic
import com.mupy.soundpy.models.Artist
import com.mupy.soundpy.ui.theme.ColorWhite

@Composable
fun ArtistCard(playlistWithMusic: PlaylistWithMusic) {
    Column(
        modifier = Modifier.padding(end = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val music = playlistWithMusic.music.getOrNull(0)
        ImageComponent(
            linkThumb = music?.thumb ?: "",
            byteArray = music?.bitImage,
            modifier = Modifier
                .size(128.dp)
                .clip(
                    CircleShape
                )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = playlistWithMusic.playlist.name ?: "",
            fontSize = 12.sp,
            color = ColorWhite,
            fontWeight = FontWeight.Bold
        )
    }
}


