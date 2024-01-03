package com.mupy.music.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mupy.music.screen.ContextMain
import com.mupy.music.ui.theme.ColorWhite
import java.io.File

@Composable
fun MyMusics(navController: NavHostController, viewModel: ContextMain) {
    val musics: MutableList<File> by viewModel.musics.observeAsState(mutableListOf())

    Text(
        text = "My musics",
        fontSize = 24.sp,
        fontWeight = FontWeight(600),
        color = ColorWhite,
        modifier = Modifier.padding(vertical = 16.dp)
    )

    LazyRow {
        items(musics.size) {
            Card(musics[it], it,navController,viewModel)
        }
    }
}