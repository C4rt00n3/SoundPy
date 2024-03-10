package com.mupy.soundpy.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mupy.soundpy.database.PlaylistWithMusic

@Composable
fun GridLayout(
    myPlaylist: Array<PlaylistWithMusic>,
    countColumns: Int = 2,
    countRows: Int = (myPlaylist.size + 1) / 2,
    horizontalArrangement: Arrangement.HorizontalOrVertical = Arrangement.SpaceBetween,
    content: @Composable (PlaylistWithMusic) -> Unit
) {
    val matrix = Array(countRows) { i ->
        Array(countColumns) { j ->
            if (i * countColumns + j < myPlaylist.size) {
                myPlaylist[i * countColumns + j]
            } else {
                null
            }
        }
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        matrix.map { array ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                horizontalArrangement = horizontalArrangement
            ) {
                array.map { playlist ->
                    playlist?.let { playlistWithMusic ->
                        content(playlistWithMusic)
                    }
                }
            }
        }
    }
}

/*Row(
                            modifier = Modifier
                                .width((minWith - 25).dp)
                                .height(50.dp)
                                .background(WhiteTransparent, ShapeDefaults.Medium),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(ShapeDefaults.Medium)
                            ) {
                                if (playlistWithMusic.music.isNotEmpty()) Utils().toBitmap(playlist.music[0].bitImage)
                                    ?.let {
                                        Image(
                                            bitmap = it.asImageBitmap(),
                                            contentDescription = "Imagem musica",
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clip(ShapeDefaults.Medium),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                            }
                            Spacer(modifier = Modifier.size(16.dp))
                            Column {
                                playlist.playlist.name?.let {
                                    Text(
                                        text = it,
                                        color = ColorWhite,
                                        fontWeight = FontWeight.W600,
                                        fontSize = 14.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                                if (playlistWithMusic.music.isNotEmpty()) Text(
                                    text = playlist.music[0].author,
                                    color = ColorWhite,
                                    fontWeight = FontWeight.W600,
                                    fontSize = 11.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }*/