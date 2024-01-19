package com.mupy.music.components

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mupy.music.R
import com.mupy.music.models.Music
import com.mupy.music.screen.ContextMain
import com.mupy.music.ui.theme.BrandColor
import com.mupy.music.ui.theme.ColorWhite
import com.mupy.music.ui.theme.LineColor
import com.mupy.music.ui.theme.TextColor2
import com.mupy.music.ui.theme.TrackColor
import com.mupy.music.utils.Utils
import java.io.File

@Composable
fun Player(viewModel: ContextMain, padding: PaddingValues, navHostController: NavHostController) {
    val pause: Boolean by viewModel.pause.observeAsState(false)
    val mediaPlayer: MediaPlayer? by viewModel.mediaPlayer.observeAsState(MediaPlayer())
    val music: Music by viewModel.music.observeAsState(Music(0, "", "", "", ""))
    val musics: MutableList<File> by viewModel.musics.observeAsState(mutableListOf())
    val currentPosition by viewModel.currentPosition.observeAsState(0)

    val progress = (currentPosition.toFloat() / 1000f) / (mediaPlayer!!.duration.toFloat() / 1000f)

    if (progress == 1f) viewModel.linearProgession()

    if (music.thumb.isNotBlank() && music.title.isNotBlank()) Container(progress, padding) {
        IconButton(
            modifier = Modifier
                .width(80.dp)
                .height(70.dp),
            onClick = {
                if(!mediaPlayer!!.isPlaying) {
                    if(mediaPlayer!!.currentPosition / 1000 < 1){
                        mediaPlayer?.stop()
                        viewModel?.setMediaPlayer()
                        val file = Utils().getFile(music.title, musics)
                        mediaPlayer?.setDataSource(file.path)
                        mediaPlayer?.prepare()
                        mediaPlayer?.start()
                        viewModel?.startTime()
                        viewModel.setPause(mediaPlayer!!.isPlaying)
                    }else{
                        mediaPlayer?.start()
                    }
                }

                navHostController.navigate("music")
        }) {
            AsyncImage(
                model = music.thumb, contentDescription = null, modifier = Modifier

                    .background(Color.Transparent)
            )
        }
        Column(
            modifier = Modifier
                .padding(end = 16.dp, top = 5.dp)
                .fillMaxHeight(),
        ) {
            Text(
                color = ColorWhite,
                text = music.title,
                fontSize = 16.sp,
                fontWeight = FontWeight(600)
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
                val utils = Utils()
                viewModel.setMediaPlayer()
                utils.moveMusic(
                    direction, musics, music, mediaPlayer!!
                ) { viewModel.setMusic(it) }
                viewModel.startTime()
                viewModel.setPause(mediaPlayer!!.isPlaying)
            }
            IconButton(onClick = {
                moveMusic("back")
            }) {
                Icon(
                    modifier = Modifier.width(60.dp),
                    painter = painterResource(id = R.drawable.left),
                    contentDescription = "Valtar para musica anterior",
                    tint = ColorWhite
                )
            }
            IconButton(onClick = {
                if (pause) {
                    mediaPlayer?.pause()
                } else {
                    mediaPlayer?.start()
                    if (!mediaPlayer!!.isPlaying) {
                        mediaPlayer?.setDataSource(musics.filter { it.name == "${music.title}.mp4" }[0].path)
                        mediaPlayer?.prepare()
                        mediaPlayer?.start()
                        viewModel.startTime()
                    }
                }
                viewModel.setPause(mediaPlayer!!.isPlaying)
            }) {
                Icon(
                    modifier = Modifier.width(60.dp), painter = painterResource(
                        id = if (pause) R.drawable.baseline_pause_24 else R.drawable.baseline_play_arrow_24
                    ), contentDescription = "Pause a musica", tint = ColorWhite
                )
            }
            IconButton(onClick = {
                moveMusic("next")
            }) {
                Icon(
                    tint = ColorWhite,
                    modifier = Modifier.width(60.dp),
                    painter = painterResource(id = R.drawable.right),
                    contentDescription = "Ir para musica anterior"
                )
            }
        }
    }
}

@Composable
private fun Container(
    progress: Float, padding: PaddingValues, call: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxHeight()
    ) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = progress,
            color = LineColor,
            trackColor = TrackColor
        )
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