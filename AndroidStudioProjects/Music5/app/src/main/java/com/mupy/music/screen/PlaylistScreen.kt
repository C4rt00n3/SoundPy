package com.mupy.music.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mupy.music.components.CardMusic
import com.mupy.music.models.Musics
import com.mupy.music.models.PlayListData
import com.mupy.music.models.PlayLists
import com.mupy.music.ui.theme.ColorWhite
import com.mupy.music.ui.theme.TextColor2

@Composable
fun PlaylistScreen(
    viewModel: ContextMain,
) {

    val playListData: PlayListData by viewModel.playlist.observeAsState(
        PlayListData(
            "", "", "", ""
        )
    )
    val playLists by viewModel.playLists.observeAsState(mutableListOf())
    println(playLists)
    val find by viewModel.find.observeAsState(PlayLists("", Musics(mutableListOf())))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    ) {
        AsyncImage(
            model = playListData.thumb_url,
            contentDescription = "playlist do genero ${playListData.genere}",
            modifier = Modifier.width(280.dp)
        )

        Text(
            text = playListData.title,
            color = ColorWhite,
            modifier = Modifier.padding(vertical = 16.dp),
            fontSize = 24.sp
        )
        Text(text = playListData.genere, color = TextColor2, fontSize = 16.sp)
        Column(
            Modifier
                .fillMaxHeight()
                .padding(bottom = 160.dp, top = 20.dp)
        ) {
            playLists?.firstOrNull { it.link == playListData.link }?.let { playlist ->
                playlist.musics?.results?.forEach { music ->
                    CardMusic(music = music)
                }
            } ?: run {
                println("Carregando>>>>>>>>>>>>>>>>")
            }
        }
    }
}
