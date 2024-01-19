package com.mupy.soundpy2.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mupy.soundpy2.ContextMain
import com.mupy.soundpy2.components.CardDefault
import com.mupy.soundpy2.ui.theme.TextColor2
import com.mupy.soundpy2.ui.theme.TrackColor
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.P)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun SearchScreen(viewModel: ContextMain, context: Context, navController: NavHostController) {
    val content by viewModel.searchYoutube.observeAsState()
    val musics by viewModel.musics.observeAsState()
    var progres by remember {
        mutableFloatStateOf(1f)
    }

    LaunchedEffect(Unit) {
        while (content?.results.isNullOrEmpty()) {
            progres++
            if (progres == 100f) progres = 0f
            delay(50.toLong())
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 9.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Seu Resultado",
            fontWeight = FontWeight(500),
            fontSize = 24.sp,
            color = TextColor2,
            modifier = Modifier.padding(vertical = 30.dp),
        )
        LazyColumn {
            content?.apply {
                items(results.size) {
                    val index = musics?.indexOfFirst { music ->
                        music.url == results[it].url
                    }
                    CardDefault(
                        item = results[it],
                        context = context,
                        viewModel = viewModel,
                        index = index ?: -1
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
        if (content?.results.isNullOrEmpty()) {
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

