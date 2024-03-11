package com.mupy.soundpy.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mupy.soundpy.ContextMain
import com.mupy.soundpy.components.GenericCardMusic
import com.mupy.soundpy.database.Music
import com.mupy.soundpy.models.Musics
import com.mupy.soundpy.ui.theme.ColorWhite
import com.mupy.soundpy.ui.theme.SoundPyTheme
import com.mupy.soundpy.ui.theme.TextColor2

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun SearchScreen(
    viewModel: ContextMain, navHostController: NavHostController, context: Context
) {
    val searchYoutube by viewModel.searchYoutube.observeAsState(Musics(listOf()))
    var musics by remember { mutableStateOf(mutableListOf<Music>()) }

    fun remove(music: Music) {
        musics = musics.filter { it.id != music.id }.toMutableList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 9.dp)
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "Resultados",
            fontSize = 24.sp,
            fontWeight = FontWeight.W500,
            color = ColorWhite,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            textAlign = TextAlign.Center,
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (musics.isEmpty()) Text(
                text = "Sem nada :(",
                fontSize = 20.sp,
                fontWeight = FontWeight.W400,
                color = TextColor2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.Center,
            )
            else LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(musics.toList()) { music ->
                    GenericCardMusic(
                        music = music,
                        viewModel = viewModel,
                        navHostController = navHostController,
                        context = context
                    ) {
                        remove(it)
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
    musics = searchYoutube.results.toMutableList()
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    val navController: NavHostController = rememberNavController()
    val context = LocalContext.current
    SoundPyTheme {
        SearchScreen(ContextMain(context, null), navController, context)
    }
}
