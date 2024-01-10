package com.mupy.music.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.mupy.music.ui.theme.WhiteTransparent
import java.io.File

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun MyMusics(navController: NavHostController, viewModel: ContextMain) {
    val musicsFile: MutableList<File> by viewModel.musicsFile.observeAsState(mutableListOf())

    Text(
        text = "My musics",
        fontSize = 24.sp,
        fontWeight = FontWeight(600),
        color = ColorWhite,
        modifier = Modifier.padding(vertical = 16.dp)
    )



    if (musicsFile.isNotEmpty()) {
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(musicsFile.size) {
                if (musicsFile[it].path.isNotBlank())
                    Card(musicsFile[it], it, navController, viewModel)
            }
        }
    } else {
        Row(modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(ScrollState(0))) {
            val empty = (1..15).toList()
            empty.map {
                Box(
                    modifier = Modifier
                        .width(290.dp)
                        .height(290.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Box(modifier = Modifier
                        .width(280.dp)
                        .height(280.dp)
                        .background(WhiteTransparent)
                        .padding(horizontal = 16.dp))
                }
            }

        }
    }
}