package com.mupy.music.screen

import android.media.MediaPlayer
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.mupy.music.models.Music
import com.mupy.music.ui.theme.BrandColor
import com.mupy.music.ui.theme.ColorWhite
import com.mupy.music.ui.theme.LineColor
import com.mupy.music.ui.theme.MusicTheme
import com.mupy.music.ui.theme.TextColor2
import com.mupy.music.ui.theme.TrackColor
import com.mupy.music.ui.theme.WhiteTransparent
import com.mupy.music.utils.Utils
import java.io.File

@Composable
fun MusicScreen(navController: NavHostController, viewModel: ContextMain) {
    val utils = Utils()

    val pause: Boolean by viewModel.pause.observeAsState(false)
    val mark: Boolean by viewModel.mark.observeAsState(false)
    val mude: Boolean by viewModel.mude.observeAsState(false)
    val mediaPlayer: MediaPlayer? by viewModel.mediaPlayer.observeAsState(MediaPlayer())
    val music: Music by viewModel.music.observeAsState(Music(0, "", "", "", ""))
    val musics: MutableList<File> by viewModel.musics.observeAsState(mutableListOf())
    val currentPosition by viewModel.currentPosition.observeAsState(0)
    val progress = (currentPosition.toFloat() / 1000f) / (mediaPlayer!!.duration.toFloat() / 1000f)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(BrandColor),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 9.dp, end = 9.dp, top = 40.dp, bottom = 65.dp)
        ) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                    contentDescription = stringResource(
                        R.string.voltar_para_pagina_principal
                    ),
                    tint = ColorWhite
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.playing_now), style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight(500),
                        color = TextColor2,
                        // fontFamily = FontFamily(Font(R.font.gilroy)),
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(vertical = 35.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = music.thumb,
                contentDescription = music.title,
                modifier = Modifier
                    .width(400.dp)
                    .background(WhiteTransparent)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = music.title, style = TextStyle(
                        fontSize = 24.sp,
                        // fontFamily = FontFamily(Font(R.font.gilroy)),
                        fontWeight = FontWeight(500),
                        color = ColorWhite,
                        textAlign = TextAlign.Center,
                    )
                )
                Text(
                    text = music.author, style = TextStyle(
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
                if(mude)
                    mediaPlayer?.setVolume(0f, 0f)
                else
                    mediaPlayer?.setVolume(1f, 1f)

                viewModel.setMude(!mude)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.volume_1),
                    contentDescription = stringResource(
                        R.string.modo_mudo
                    ),
                    tint = if (mude) ColorWhite else TextColor2
                )
            }
            Row {
                IconButton(
                    onClick = {
                        if (!mark) mediaPlayer?.setOnCompletionListener {
                            // A música terminou, reinicie a reprodução
                            mediaPlayer?.seekTo(0)
                            mediaPlayer?.start()
                            viewModel.startTime()
                        }
                        else mediaPlayer?.setOnCompletionListener {
                            mediaPlayer?.setOnCompletionListener {
                                utils.moveMusic("next", musics,music, mediaPlayer!!) {
                                    viewModel.setMusic(
                                        it
                                    )
                                }
                            }
                        }
                        viewModel.setMark(!mark)
                    },
                    modifier = Modifier.padding(end = 10.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_round_repeat),
                        contentDescription = stringResource(R.string.repetir_a_musica),
                        tint = if (mark) ColorWhite else TextColor2
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.eva_shuffle_outline),
                        contentDescription = stringResource(
                            R.string.repitir_somente_1_vezes
                        ),
                        tint = if (!mark) ColorWhite else TextColor2
                    )
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 60.dp)
        ) {
            val currentPosition by viewModel.currentPosition.observeAsState(0)

            fun timeUse(time: Int): String {
                val seconds = time / 1000f
                val minutes = seconds / 60
                val remainingSeconds = seconds % 60
                return String.format("%02d:%02d", minutes.toInt(), remainingSeconds.toInt())
            }
            Text(
                text = timeUse((currentPosition)),
                color = TextColor2,
                fontSize = 12.sp,
                fontWeight = FontWeight.W400
            )
            Text(text = timeUse(mediaPlayer!!.duration), color = TextColor2)
        }
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 35.dp, bottom = 80.dp),
            progress = progress,
            color = LineColor,
            trackColor = TrackColor
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row() {
                IconButton(onClick = {
                    viewModel.setMediaPlayer()
                    utils.moveMusic("back", musics, music, mediaPlayer!!) { viewModel.setMusic(it) }
                    viewModel.setPause(mediaPlayer!!.isPlaying)
                    viewModel.startTime()
                    // viewModel.linearProgession()
                }) {
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
                    if (mediaPlayer!!.isPlaying) mediaPlayer?.pause()
                    else mediaPlayer?.start()
                    viewModel.setPause(mediaPlayer!!.isPlaying)
                    // viewModel.linearProgession()
                }) {
                    Icon(
                        modifier = Modifier
                            .width(140.dp)
                            .height(140.dp), painter = painterResource(
                            id = if (pause) R.drawable.baseline_pause_24 else R.drawable.baseline_play_arrow_24
                        ), contentDescription = "Pause a musica", tint = ColorWhite
                    )
                }
                IconButton(onClick = {
                    viewModel.setMediaPlayer()
                    utils.moveMusic("next", musics, music, mediaPlayer!!) { viewModel.setMusic(it) }
                    viewModel.setPause(mediaPlayer!!.isPlaying)
                    viewModel.startTime()
                    // viewModel.linearProgession()
                }) {
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

@Preview(showBackground = true)
@Composable
fun MusicPreview() {
    MusicTheme {
        MusicScreen(NavHostController(LocalContext.current), ContextMain())
    }
}