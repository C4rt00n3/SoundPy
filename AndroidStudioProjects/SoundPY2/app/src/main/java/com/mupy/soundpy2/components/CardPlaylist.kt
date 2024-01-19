package com.mupy.soundpy2.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mupy.soundpy2.ContextMain
import com.mupy.soundpy2.models.PlayListData
import com.mupy.soundpy2.ui.theme.ColorWhite
import com.mupy.soundpy2.ui.theme.WhiteTransparent

@Composable
fun CardPlaylist(navController: NavHostController?, viewModel: ContextMain) {

    val playlist by viewModel.playListsCards.observeAsState(listOf())

    LazyRow {
        if (playlist.isEmpty()) {
            items(5) {
                Box(
                    modifier = Modifier
                        .width(280.dp)
                        .height(280.dp)
                        .background(WhiteTransparent)
                )
                Spacer(modifier = Modifier.width(20.dp))
            }
        } else {
            items(playlist.size ?: 0) { int ->
                playlist[int].let { item ->
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        onClick = {
                            try {
                                viewModel.fetchPlaylist(item.link)
                                viewModel.setPlaylisData(item)
                            } catch (error: Exception) {
                                error.printStackTrace()
                            } finally {
                                navController?.navigate("playlist")
                            }
                        },
                        modifier = Modifier
                            .width(280.dp)
                            .height(280.dp),
                        shape = RectangleShape
                    ) {
                        Image(item = item)
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                }
            }
        }
    }
}

@Composable
private fun Image(item: PlayListData) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = item.thumb_url,
            contentDescription = "Playlist ${item.title}. Click para abrir.",
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
                .background(WhiteTransparent),
            contentScale = ContentScale.Crop,
        )
        Text(text = item.title, fontWeight = FontWeight(500), fontSize = 18.sp, color = ColorWhite)
    }
}