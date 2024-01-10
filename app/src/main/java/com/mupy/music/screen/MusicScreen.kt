package com.mupy.music.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mupy.music.R
import com.mupy.music.components.Progress
import com.mupy.music.ui.theme.BrandColor
import com.mupy.music.ui.theme.ColorWhite
import com.mupy.music.ui.theme.MusicTheme
import com.mupy.music.ui.theme.TextColor2
import com.mupy.music.ui.theme.WhiteTransparent
import com.mupy.music.utils.PlayerMusic

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun MusicScreen(navController: NavHostController, viewModel: ContextMain) {
    val pause: Boolean by viewModel.pause.observeAsState(false)
    val mute: Boolean by viewModel.mute.observeAsState(false)
    val reapt: Boolean by viewModel.reapt.observeAsState(false)
    val music by viewModel.music.observeAsState()
    val player by viewModel.player.observeAsState(PlayerMusic(mutableListOf()))
    val currentPosition by viewModel.currentPosition.observeAsState(0)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(BrandColor),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(end = 35.dp, start = 35.dp, top = 90.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(vertical = 20.dp)) {
                Text(
                    text = "Play now",
                    fontSize = 24.sp,
                    fontWeight = FontWeight(500),
                    color = TextColor2
                )
            }
            if (music?.bitImage != null) {
                music?.bitImage?.asImageBitmap()?.let {
                    Image(
                        bitmap = it,
                        contentDescription = music?.title,
                        modifier = Modifier
                            .width(400.dp)
                            .background(WhiteTransparent)
                    )
                }
            } else {
                AsyncImage(
                    model = music?.thumb, contentDescription = "", modifier = Modifier.width(400.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${music?.title}", style = TextStyle(
                        fontSize = 24.sp,
                        // fontFamily = FontFamily(Font(R.font.gilroy)),
                        fontWeight = FontWeight(500),
                        color = ColorWhite,
                        textAlign = TextAlign.Center,
                    ), maxLines = 1
                )
                Text(
                    text = "${music?.author}", style = TextStyle(
                        fontSize = 16.sp,
                        // fontFamily = FontFamily(Font(R.font.gilroy)),
                        fontWeight = FontWeight(400),
                        color = TextColor2,

                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            IconButton(onClick = {
                if (!mute) player.mute()
                else player.unMute()
                viewModel.setMute(!mute)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.volume_1),
                    contentDescription = stringResource(
                        R.string.modo_mudo
                    ),
                    tint = if (mute) ColorWhite else TextColor2
                )
            }
            Row {
                IconButton(
                    onClick = {
                        viewModel.setReapt(!reapt)
                    },
                    modifier = Modifier.padding(end = 10.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_round_repeat),
                        contentDescription = stringResource(R.string.repetir_a_musica),
                        tint = if (reapt) ColorWhite else TextColor2
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.eva_shuffle_outline),
                        contentDescription = stringResource(
                            R.string.repitir_somente_1_vezes
                        ),
                        tint = if (!reapt) ColorWhite else TextColor2
                    )
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 60.dp, bottom = 30.dp)
        ) {
            Text(
                text = player.timeUse((currentPosition)),
                color = TextColor2,
                fontSize = 12.sp,
                fontWeight = FontWeight.W400
            )
            Text(text = player.timeUse(player.duration()), color = TextColor2)
        }
        Progress(viewModel = viewModel)
        Row(
            modifier = Modifier.fillMaxWidth().padding(top=60.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                IconButton(onClick = { player.back()?.let { viewModel.setMusic(it) } }) {
                    Icon(
                        modifier = Modifier
                            .width(140.dp)
                            .height(140.dp),
                        painter = painterResource(id = R.drawable.left),
                        contentDescription = "Valtar para musica anterior",
                        tint = ColorWhite
                    )
                }
                IconButton(modifier = Modifier.padding(horizontal = 33.dp), onClick = {
                    if (pause) player.pause()
                    else player.start()
                    viewModel.setPause(player.isPlaying())
                }) {
                    Icon(
                        modifier = Modifier
                            .width(140.dp)
                            .height(140.dp), painter = painterResource(
                            id = if (pause) R.drawable.baseline_pause_24 else R.drawable.baseline_play_arrow_24
                        ), contentDescription = "Pause a musica", tint = ColorWhite
                    )
                }
                IconButton(onClick = { player.next()?.let { viewModel.setMusic(it) } }) {
                    Icon(
                        tint = ColorWhite,
                        modifier = Modifier
                            .width(140.dp)
                            .height(140.dp),
                        painter = painterResource(id = R.drawable.right),
                        contentDescription = "Ir para musica anterior"
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
fun MusicPreview() {
    MusicTheme {
        MusicScreen(NavHostController(LocalContext.current), ContextMain())
    }
}