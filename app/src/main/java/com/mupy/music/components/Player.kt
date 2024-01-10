package com.mupy.music.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mupy.music.R
import com.mupy.music.models.Music
import com.mupy.music.screen.ContextMain
import com.mupy.music.ui.theme.BrandColor
import com.mupy.music.ui.theme.ColorWhite
import com.mupy.music.ui.theme.TextColor2
import com.mupy.music.ui.theme.WhiteTransparent
import com.mupy.music.utils.PlayerMusic

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun Player(viewModel: ContextMain, padding: PaddingValues, navHostController: NavHostController) {
    val pause: Boolean by viewModel.pause.observeAsState(false)
    val music: Music by viewModel.music.observeAsState(Music(0, "", "", "", "", "",null))
    val player: PlayerMusic by viewModel.player.observeAsState(PlayerMusic(mutableListOf()))
    val reapt by viewModel.reapt.observeAsState(false)
    val loading by viewModel.loading.observeAsState(true)

    if (pause && !reapt) player.setOnCompletionListener {
        player.next()?.let {
            viewModel.setMusic(it)
        }
    }
    else player.reapt()

    if (music.thumb.isNotBlank() && music.title.isNotBlank()) Container(
        padding, viewModel,
    ) {
        IconButton(onClick = {
            navHostController.navigate("music")
        }, modifier = Modifier.size(80.dp)) {
            if (music.bitImage != null) {
                music.bitImage?.asImageBitmap()?.let {
                    Image(
                        bitmap = it,
                        contentDescription = music.title,
                        modifier = Modifier
                            .background(WhiteTransparent)

                    )
                }
            } else {
                AsyncImage(
                    modifier = Modifier
                        .background(WhiteTransparent),
                    model = music.thumb,
                    contentDescription = "Imagem da musica ${music.title}"
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(start = 9.dp, end = 16.dp, top = 5.dp)
                .fillMaxHeight(),
        ) {
            Text(
                color = ColorWhite,
                text = music.title,
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                modifier = Modifier.widthIn(max = 200.dp, min = 200.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = music.author,
                color = TextColor2,
                fontSize = 12.sp,
                fontWeight = FontWeight(500)
            )
        }
        Row(
            modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically
        ) {
            fun moveMusic(direction: String) {
                if (direction == "next") {
                    val music = player.next()
                    music?.let { viewModel.setMusic(it) }
                    viewModel.setPause(player.isPlaying())
                } else {
                    val music = player.back()
                    music?.let { viewModel.setMusic(it) }
                    viewModel.setPause(player.isPlaying())
                }
                viewModel.startTime()
            }
            IconButton(onClick = {
                moveMusic("back")
            }) {
                Icon(
                    modifier = Modifier.width(60.dp),
                    painter = painterResource(id = R.drawable.left),
                    contentDescription = stringResource(R.string.valtar_para_musica_anterior),
                    tint = ColorWhite
                )
            }
            IconButton(enabled = loading, onClick = {
                if (!pause) {
                    player.start()
                    viewModel.startTime()
                } else player.pause()
                viewModel.setPause(player.isPlaying())
            }) {
                Icon(
                    modifier = Modifier.width(60.dp),
                    painter = painterResource(
                        id = if (pause) R.drawable.baseline_pause_24 else R.drawable.baseline_play_arrow_24
                    ),
                    contentDescription = stringResource(R.string.pause_a_musica),
                    tint = ColorWhite
                )
            }
            IconButton(onClick = {
                moveMusic("next")
            }) {
                Icon(
                    tint = ColorWhite,
                    modifier = Modifier.width(60.dp),
                    painter = painterResource(id = R.drawable.right),
                    contentDescription = stringResource(R.string.ir_para_musica_anterior)
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
private fun Container(
    padding: PaddingValues, viewModel: ContextMain, call: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxHeight()
            .background(BrandColor)
    ) {
        // Slider para o progresso do áudio
        Progress(viewModel = viewModel)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(BrandColor)
                .padding(horizontal = 9.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                call()
            }
        }
    }
}