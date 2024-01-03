package com.mupy.music.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mupy.music.models.Music
import com.mupy.music.ui.theme.BrandColor
import com.mupy.music.ui.theme.ColorWhite
import com.mupy.music.ui.theme.MusicTheme
import com.mupy.music.ui.theme.TextColor2
import com.mupy.music.ui.theme.WhiteTransparent

@Composable
fun CardMusic(music: Music) {
    val background =
        ButtonDefaults.buttonColors(containerColor = if (music.url.isNotBlank()) Color.Transparent else WhiteTransparent)

    Button(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {},
        colors = background,
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(BrandColor)
                .height(120.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = music.thumb,
                contentDescription = null,
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp)
                    .padding(end = 16.dp),
            )
            Column {
                Text(text = music.title, color = ColorWhite)
                Text(
                    text = music.author,
                    fontSize = 12.sp,
                    color = TextColor2,
                    fontWeight = FontWeight(400)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MusicPreview() {
    MusicTheme {
        CardMusic(Music(0, "test", "", "Text", ""))
    }
}