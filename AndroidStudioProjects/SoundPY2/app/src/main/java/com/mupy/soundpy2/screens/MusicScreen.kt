package com.mupy.soundpy2.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mupy.soundpy2.ContextMain
import com.mupy.soundpy2.R
import com.mupy.soundpy2.components.Header
import com.mupy.soundpy2.components.Progress
import com.mupy.soundpy2.models.Music
import com.mupy.soundpy2.ui.theme.BrandColor
import com.mupy.soundpy2.ui.theme.ColorWhite
import com.mupy.soundpy2.ui.theme.TextColor2
import com.mupy.soundpy2.ui.theme.WhiteTransparent
import com.mupy.soundpy2.utils.Play

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun MusicScreen(viewModel: ContextMain) {
    val carouselImages by viewModel.musics.observeAsState(mutableListOf())
    val count by viewModel.count.observeAsState(0)
    val reapt by viewModel.reapt.observeAsState(false)
    val mute by viewModel.mute.observeAsState(false)
    val pause by viewModel.pause.observeAsState(false)
    val player by viewModel.player.observeAsState(Play())
    val musics by viewModel.musics.observeAsState()
    viewModel.setPageState(rememberPagerState(initialPage = count) {
        musics?.size ?: 0
    })
    val currentPosition by viewModel.currentPosition.observeAsState(0)
    val pageState by viewModel.pageState.observeAsState()

    viewModel.setMusic(carouselImages[pageState?.currentPage!!])
    if (count != pageState?.currentPage) pageState?.currentPage?.let {
        viewModel.open(it)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BrandColor)
            .padding(horizontal = 9.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Card(
                colors = CardDefaults.cardColors(Color.Transparent),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            ) {
                HorizontalPager(
                    modifier = Modifier.fillMaxWidth(), state = pageState!!, pageSpacing = 10.dp
                ) { page ->
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ImageUse(id = page, music = carouselImages[page])
                        Text(
                            text = carouselImages[page].title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 24.sp,
                            fontFamily = FontFamily.Serif,
                            color = ColorWhite,
                            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 10.dp)
                        )
                        Text(
                            text = carouselImages[page].author,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Serif,
                            color = TextColor2,
                            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 10.dp)
                        )
                    }
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = {
                print("Mute")
                if (!mute) viewModel.mute()
                else viewModel.unMute()
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
                IconButton(onClick = {
                    viewModel.setReapt(!reapt)
                }) {
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = player.timeUse((currentPosition)),
                color = TextColor2,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400
            )

            Text(
                text = player.timeUse(player.duration),
                color = TextColor2,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp)
        ) {
            Progress(viewModel)
        }
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    viewModel.back()
                    viewModel.scrollToPage()
                },
                modifier = Modifier
                    .size(100.dp)
                    .padding(vertical = 20.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                contentPadding = PaddingValues(0.dp)

            ) {
                Icon(
                    Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "Iniciar Musica",
                    modifier = Modifier.fillMaxSize(),
                    tint = ColorWhite
                )
            }
            Button(
                onClick = {
                    if (pause) {
                        player.pause()
                    } else {
                        player.start()
                    }
                    viewModel.setPause(player.isPlaying)
                    viewModel.setOnCompletionListener()
                    viewModel.startTime()
                },
                modifier = Modifier
                    .size(100.dp)
                    .padding(vertical = 20.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    painter = if (pause) painterResource(id = R.drawable.baseline_pause_24) else painterResource(
                        id = R.drawable.baseline_play_arrow_24
                    ),
                    contentDescription = "Iniciar Musica",
                    modifier = Modifier.fillMaxSize(),
                    tint = ColorWhite
                )
            }
            Button(
                onClick = {
                    viewModel.next()
                    viewModel.scrollToPage()
                },
                modifier = Modifier
                    .size(100.dp)
                    .padding(vertical = 20.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    Icons.Filled.KeyboardArrowRight,
                    contentDescription = "Iniciar Musica",
                    modifier = Modifier.fillMaxSize(),
                    tint = ColorWhite
                )
            }

        }
    }
}

@Composable
private fun ImageUse(
    id: Int,
    music: Music,
) {
    val modifier: Modifier = Modifier
        .padding(end = 9.dp, start = 9.dp, bottom = 9.dp)
        .background(WhiteTransparent)
        .shadow(if (id == 2) 5.dp else 0.dp)
    val use = modifier.size(300.dp)
    if (music.bitImage == null) AsyncImage(
        model = music.thumb,
        contentDescription = "imagem da musica ${music.title}",
        modifier = use,
        contentScale = ContentScale.Crop,

        )
    else Image(
        bitmap = music.bitImage.asImageBitmap(),
        contentDescription = "imagem da musica ${music.title}",
        modifier = use,
        contentScale = ContentScale.Crop
    )
}
