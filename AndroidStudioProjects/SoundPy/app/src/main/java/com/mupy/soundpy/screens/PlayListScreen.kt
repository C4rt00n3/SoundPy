package com.mupy.soundpy.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mupy.soundpy.ContextMain
import com.mupy.soundpy.R
import com.mupy.soundpy.components.GenericCardMusic
import com.mupy.soundpy.components.ImageComponent
import com.mupy.soundpy.database.Music
import com.mupy.soundpy.models.PlayListData
import com.mupy.soundpy.ui.theme.ColorWhite
import com.mupy.soundpy.ui.theme.SoundPyTheme
import com.mupy.soundpy.ui.theme.WhiteTransparent

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun PlaylistScreen(
    viewModel: ContextMain, navHostController: NavHostController, context: Context
) {
    val playlistData by viewModel.playListData.observeAsState(null)
    val playlist by viewModel.playlists.observeAsState(mutableListOf())
    val current = playlist.find { it.link == playlistData?.url }
    var musics by remember { mutableStateOf(mutableListOf<Music>()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black)
        //.verticalScroll(ScrollState(0))
        , horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        TopPlaylistCard(playlistData) {
            navHostController.navigateUp()
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 9.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.action_shuffle),
                    contentDescription = stringResource(
                        id = R.string.progresso_linear
                    ),
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.action_music_play_green),
                    contentDescription = stringResource(
                        id = R.string.iniciar_playlist
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 9.dp)
        ) {
            items(musics) { music ->
                GenericCardMusic(
                    music = music,
                    viewModel = viewModel,
                    navHostController = navHostController,
                    context = context,
                ) {
                    musics = musics.filter { it.id != music.id }.toMutableList()
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
    if (current != null) musics = current.musics
}

@Composable
private fun TopPlaylistCard(playlistData: PlayListData?, onclick: () -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        ImageComponent(
            contentScale = ContentScale.Companion.Crop,
            linkThumb = playlistData?.thumb ?: "",
            byteArray = null,
            contentDescription = stringResource(R.string.imagem_da_playlist),
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.75f)
                .height(300.dp)
                .background(WhiteTransparent)
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 9.dp)
                .height(300.dp)
        ) {
            IconButton(
                onClick = { onclick() },
                modifier = Modifier.clip(CircleShape),
                colors = IconButtonColors(
                    containerColor = Color.Black.copy(0.5f),
                    contentColor = ColorWhite,
                    disabledContainerColor = Color.Black.copy(0.3f),
                    disabledContentColor = ColorWhite.copy(0.5f)
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = stringResource(R.string.volar_para_pagina_anterior),
                    tint = ColorWhite
                )
            }
            Text(
                text = playlistData?.title ?: "Loading...",
                fontWeight = FontWeight.W400,
                color = ColorWhite,
                fontSize = 30.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
private fun PlaylistScreenPreview() {
    SoundPyTheme {
        val navController: NavHostController = rememberNavController()
        PlaylistScreen(
            ContextMain(LocalContext.current, null), navController, LocalContext.current
        )
    }
}