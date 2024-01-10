package com.mupy.music.screen

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mupy.music.R
import com.mupy.music.components.CardMusic
import com.mupy.music.models.PlayListData
import com.mupy.music.ui.theme.ColorWhite
import com.mupy.music.ui.theme.TextColor2

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun PlaylistScreen(
    navController: NavHostController,
    context: Context,
    viewModel: ContextMain,
) {

    val playListData: PlayListData by viewModel.playlist.observeAsState(
        PlayListData(
            "", "", "", ""
        )
    )
    val playLists by viewModel.playLists.observeAsState(mutableListOf())
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 90.dp)
    ) {
        val matrix = ColorMatrix()
        matrix.setToSaturation(1f)
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(playListData.thumb_url)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.playlist_do_genero) + playListData.genere,
            contentScale = ContentScale.Crop,
            modifier = Modifier.width(250.dp),
            colorFilter = ColorFilter.colorMatrix(matrix)
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
            playLists.firstOrNull { it.link == playListData.link }?.let { playlist ->
                playlist.musics.forEach { music ->
                    CardMusic(
                        music = music,
                        navController = navController,
                        context,
                        viewModel = viewModel
                    )
                }
            } ?: run {
            }
        }
    }
}
