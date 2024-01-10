package com.mupy.music.components

import android.content.Context
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mupy.music.R
import com.mupy.music.models.Music
import com.mupy.music.screen.ContextMain
import com.mupy.music.ui.theme.ColorWhite
import com.mupy.music.ui.theme.TextColor2
import com.mupy.music.utils.PlayerMusic
import com.mupy.music.utils.Utils

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun CardMusic(
    music: Music, navController: NavHostController, context: Context, viewModel: ContextMain
) {
    val musics by viewModel.musics.observeAsState(mutableListOf())
    val musicP by viewModel.music.observeAsState()
    val musicsFile by viewModel.musicsFile.observeAsState(mutableListOf())
    val player by viewModel.player.observeAsState(PlayerMusic(mutableListOf()))
    val isButtonEnabled = remember { mutableStateOf(true) }

    val pick = musics.filter { it.name == music.title || it.name == music.name }
    val check = if (pick.isNotEmpty()) musics.indexOf(pick[0]) else -1

    if (check >= 0) {
        isButtonEnabled.value = false
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        viewModel.getFiles()
                        if (!isButtonEnabled.value) {
                            player.open(check)?.let {
                                viewModel.setMusic(it)
                                viewModel.startTime()
                                viewModel.setPause(player.isPlaying())
                            }
                        }
                        else {
                            viewModel.setCurrentPosition(0)
                            viewModel.setLoading(false)
                            player.stop()
                            viewModel.setProgress(0f)
                            println(music.url)
                            viewModel.download(music.url, music.title, context, false)
                        }
                        viewModel.getFiles()
                        navController.navigate("music")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    shape = RectangleShape,
                    modifier = Modifier.widthIn(200.dp, 380.dp)
                ) {
                    if (music.bitImage == null) AsyncImage(
                        model = music.thumb,
                        contentDescription = null,
                        modifier = Modifier
                            .width(120.dp)
                            .height(80.dp)
                            .padding(end = 16.dp),
                    )
                    else Image(
                        bitmap = music.bitImage.asImageBitmap(),
                        contentDescription = "Imagem da musica",
                        modifier = Modifier
                            .width(120.dp)
                            .height(80.dp)
                            .padding(end = 16.dp),
                    )
                    Column {
                        Text(
                            text = music.title,
                            color = ColorWhite,
                            maxLines = 1,
                            modifier = Modifier.widthIn(max = 260.dp)
                        )
                        Text(
                            text = music.author,
                            fontSize = 12.sp,
                            color = TextColor2,
                            fontWeight = FontWeight(400)
                        )

                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 16.dp)
            ) {
                IconButton(
                    onClick = {
                        if (isButtonEnabled.value) {
                            viewModel.download(music.url, music.title, context, true)
                            isButtonEnabled.value = false
                        } else {
                            Utils().getFile("${music.name}", musicsFile)?.let {
                                println("Excluido com sucesso!")
                                it.delete()
                                viewModel.getFiles()
                            }
                        }
                    }, modifier = Modifier.width(24.dp)
                ) {
                    if (isButtonEnabled.value) Icon(
                        painter = painterResource(id = R.drawable.baseline_downloading_24),
                        contentDescription = stringResource(
                            R.string.baixar_muisica
                        ) + music.title,
                        tint = Color.Gray
                    )
                    else Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Excluir a musica" + music.title,
                        tint = Color.Gray
                    )
                }
            }
        }
        if(musicP?.title == music.title || musicP?.title == music.name)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .height(2.dp)
            ) {}
    }
}