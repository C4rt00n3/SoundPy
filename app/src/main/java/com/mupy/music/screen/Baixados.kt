package com.mupy.music.screen

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mupy.music.components.CardMusic
import com.mupy.music.ui.theme.TextColor2
import com.mupy.music.utils.Utils

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun BaixadosScreen(context: Context, navHostController: NavHostController, viewModel: ContextMain) {
    val musics by viewModel.musicsFile.observeAsState(mutableListOf())
    LaunchedEffect(Unit) {
        viewModel.getFiles()
    }
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 90.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Baixados",
                fontSize = 24.sp,
                fontWeight = FontWeight(500),
                color = TextColor2
            )
        }
        Column(
            modifier = Modifier.padding(top = 40.dp, bottom = 160.dp),
        ) {
            if (musics.isNotEmpty()) musics.mapIndexed { index, file ->
                Utils().setMusic(index, file.path)?.let {
                    CardMusic(
                        music = it,
                        navController = navHostController,
                        context = context,
                        viewModel = viewModel
                    )
                }
            }
            if (musics.isEmpty())
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Sem nada!", fontSize = 24.sp, color = TextColor2)
                }
        }
    }
}