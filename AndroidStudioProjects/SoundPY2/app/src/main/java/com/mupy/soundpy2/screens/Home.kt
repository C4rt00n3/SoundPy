package com.mupy.soundpy2.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.mupy.soundpy2.ContextMain
import com.mupy.soundpy2.components.CardMusic
import com.mupy.soundpy2.components.CardPlaylist
import com.mupy.soundpy2.ui.theme.BrandColor
import com.mupy.soundpy2.ui.theme.ColorWhite
import com.mupy.soundpy2.ui.theme.SoundPY2Theme

@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun Home(navController: NavHostController?, context: Context, viewModel: ContextMain) {
    val musics by viewModel.musics.observeAsState(mutableListOf())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 9.dp)
            .background(BrandColor)
    ) {
        Text(
            text = "Recommended for you",
            fontSize = 24.sp,
            fontWeight = FontWeight(600),
            color = ColorWhite,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        CardPlaylist(viewModel = viewModel, navController = navController)
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "My Musics",
            fontSize = 24.sp,
            fontWeight = FontWeight(600),
            color = ColorWhite,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        LazyRow {
            items(musics.size ?: 0) {
                CardMusic(
                    music = musics.get(it),
                    navHostController = navController,
                    context = context,
                    id = it,
                    viewModel = viewModel,
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    SoundPY2Theme {
        Home(null, LocalContext.current, ContextMain())
    }
}
