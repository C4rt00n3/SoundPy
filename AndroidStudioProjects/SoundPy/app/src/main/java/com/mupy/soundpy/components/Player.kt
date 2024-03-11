package com.mupy.soundpy.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mupy.soundpy.ContextMain
import com.mupy.soundpy.R
import com.mupy.soundpy.database.Music
import com.mupy.soundpy.ui.theme.ColorWhite
import com.mupy.soundpy.ui.theme.LineColor
import com.mupy.soundpy.ui.theme.SoundPyTheme
import com.mupy.soundpy.ui.theme.TextColor2
import com.mupy.soundpy.utils.SoundPy


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun Player(
    viewModel: ContextMain, navController: NavHostController,
) {
    val music: Music? by viewModel.music.observeAsState(null)
    val soundPy: SoundPy? by viewModel.soundPy.observeAsState(null)
    val pause by viewModel.pause.observeAsState(false)
    val palette by viewModel.palette.observeAsState(null)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(
                ColorWhite.copy(0.05f), ShapeDefaults.Medium
            ),
    ) {
        Progress(
            viewModel = viewModel,
            activeTrackColor = Color(
                palette?.getVibrantColor(Color.Black.hashCode()) ?: LineColor.hashCode(),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp),
            thumbColor = Color.Transparent
        )
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .height(60.dp)
                    .clip(ShapeDefaults.Medium)
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = { navController.navigate("favorites") })
                    },
                // n
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically, modifier = Modifier.widthIn(
                        max = (LocalConfiguration.current.screenWidthDp - 200).dp
                    )
                ) {
                    ImageComponent(
                        music?.thumb ?: "",
                        byteArray = music?.bitImage,
                        modifier = Modifier
                            .width(60.dp)
                            .fillMaxHeight(),
                        contentDescription = stringResource(id = R.string.imagem_da_musica) + music?.title,
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    Column {
                        Text(
                            text = music?.title ?: stringResource(R.string.carregando),
                            maxLines = 1,
                            fontSize = 16.sp,
                            color = ColorWhite,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = music?.author ?: stringResource(R.string.carregando),
                            maxLines = 1,
                            fontSize = 12.sp,
                            color = TextColor2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(60.dp)
            ) {
                IconButton(onClick = {
                    @Suppress("DEPRECATION") soundPy?.player?.previous()
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.action_previous),
                        contentDescription = stringResource(R.string.ir_para_musica_passada),
                    )
                }
                IconButton(onClick = {
                    if (!pause) {
                        soundPy?.player?.play()
                    } else {
                        soundPy?.player?.pause()
                    }
                }) {
                    Image(
                        painter = painterResource(id = if (pause) R.drawable.action_pause else R.drawable.action_play),
                        contentDescription = stringResource(R.string.ir_para_musica_passada),
                    )
                }
                IconButton(onClick = {
                    @Suppress("DEPRECATION") soundPy?.player?.next()
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.action_next),
                        contentDescription = stringResource(R.string.ir_para_musica_passada),
                    )
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
fun PlayerPreview() {
    val navController: NavHostController = rememberNavController()
    SoundPyTheme {
        Player(
            ContextMain(LocalContext.current, null), navController,
        )
    }
}