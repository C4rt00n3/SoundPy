package com.mupy.soundpy2.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mupy.soundpy2.ContextMain
import com.mupy.soundpy2.R
import com.mupy.soundpy2.models.Music
import com.mupy.soundpy2.ui.theme.BrandColor
import com.mupy.soundpy2.ui.theme.ColorWhite
import com.mupy.soundpy2.ui.theme.TextColor2
import com.mupy.soundpy2.utils.Play

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun Player(viewModel: ContextMain, padding: PaddingValues, navHostController: NavHostController) {
    val music by viewModel.music.observeAsState(null)
    val pause by viewModel.pause.observeAsState(false)
    val player by viewModel.player.observeAsState(Play())
    val musics by viewModel.musics.observeAsState(mutableListOf())
    val count by viewModel.count.observeAsState(0)
    val pageState = rememberPagerState(initialPage = count) {
        musics.size
    }

    if (player.isPlaying) viewModel.setOnCompletionListener()

    Column(
        modifier = Modifier
            .background(BrandColor)
            .padding(bottom = 10.dp),
    ) {
        Progress(viewModel = viewModel)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                shape = RectangleShape,
                onClick = {
                    viewModel.setPageState(pageState)
                    navHostController.navigate("music")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp),
            ) {
                Row(modifier = Modifier.padding(horizontal = 9.dp)) {
                    ImageUse(music = music)
                    Column(modifier = Modifier.widthIn(max = 200.dp)) {
                        Text(
                            text = music?.title ?: "Carregando...",
                            fontWeight = FontWeight(500),
                            fontSize = 14.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.widthIn(max = 200.dp)
                        )
                        Text(
                            text = music?.author ?: "Carregando...",
                            fontWeight = FontWeight(500),
                            fontSize = 12.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = TextColor2,
                            modifier = Modifier.widthIn(max = 200.dp)
                        )
                    }
                }
            }
            Row {
                IconButton(onClick = {
                    viewModel.back()
                }) {
                    Icon(
                        Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Iniciar Musica",
                        modifier = Modifier.size(40.dp),
                        tint = ColorWhite
                    )
                }
                IconButton(onClick = {
                    if (pause) {
                        player.pause()
                    } else {
                        player.start()
                    }
                    viewModel.setPause(player.isPlaying)
                    viewModel.startTime()
                    viewModel.setOnCompletionListener()
                }) {
                    Icon(
                        painter = if (pause) painterResource(id = R.drawable.baseline_pause_24) else painterResource(
                            id = R.drawable.baseline_play_arrow_24
                        ),
                        contentDescription = "Iniciar Musica",
                        modifier = Modifier.size(40.dp),
                        tint = ColorWhite
                    )
                }
                IconButton(onClick = {
                    viewModel.next()
                }) {
                    Icon(
                        Icons.Filled.KeyboardArrowRight,
                        contentDescription = "Iniciar Musica",
                        modifier = Modifier.size(40.dp),
                        tint = ColorWhite
                    )
                }

            }
        }
    }
}

@Composable
private fun ImageUse(music: Music?) {
    val modifier = Modifier
        .width(80.dp)
        .height(60.dp)
        .padding(end = 9.dp)
    if (music?.bitImage != null) androidx.compose.foundation.Image(
        bitmap = music.bitImage.asImageBitmap(),
        contentDescription = "A musica ${music.title}",
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
    else AsyncImage(
        model = music?.thumb,
        contentDescription = "A musica ${music?.title}",
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}