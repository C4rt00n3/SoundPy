package com.mupy.soundpy2.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mupy.soundpy2.ContextMain
import com.mupy.soundpy2.R
import com.mupy.soundpy2.components.CardDefault
import com.mupy.soundpy2.components.Header
import com.mupy.soundpy2.models.PlayListData
import com.mupy.soundpy2.ui.theme.ColorWhite
import com.mupy.soundpy2.ui.theme.TextColor2
import com.mupy.soundpy2.ui.theme.TrackColor
import com.mupy.soundpy2.ui.theme.WhiteTransparent
import kotlinx.coroutines.delay

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun PlaylistList(navController: NavHostController, context: Context, viewModel: ContextMain) {
    var progres by remember {
        mutableFloatStateOf(1f)
    }
    val playListUse by viewModel.playLists.observeAsState(mutableListOf())
    val musics by viewModel.musics.observeAsState(mutableListOf())
    val playListDataUse by viewModel.playListData.observeAsState(PlayListData("", "", "", ""))

    LaunchedEffect(Unit) {
        while (playListUse.isEmpty()) {
            progres++
            if (progres == 100f) progres = 0f
            delay(50.toLong())
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(ScrollState(0))
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(playListDataUse.thumb_url)
                    .crossfade(true).build(),
                contentDescription = "Imagem da playlis ${playListDataUse.title}.",
                modifier = Modifier
                    .size(250.dp)
                    .background(WhiteTransparent),
            )
            Text(
                text = playListDataUse.title ?: stringResource(R.string.carregando),
                color = ColorWhite,
                modifier = Modifier.padding(vertical = 16.dp),
                fontSize = 24.sp
            )
            Text(
                text = playListDataUse.genere ?: stringResource(R.string.carregando),
                color = TextColor2,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                playListUse.firstOrNull { it.link == playListDataUse.link }?.let { playlist ->
                    playlist.musics.forEach { music ->
                        val index = musics.indexOfFirst {
                            // println("${it.url} | ${music.url}")
                            it.url == music.url
                        }
                        CardDefault(
                            item = music, context = context, viewModel = viewModel, index = index
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
            if (playListUse.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        progress = progres / 100f,
                        trackColor = TrackColor,
                        color = TextColor2,
                        modifier = Modifier.size(60.dp),
                        strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth,
                        strokeCap = ProgressIndicatorDefaults.CircularIndeterminateStrokeCap,
                    )
                }
            }
        }
    }
}