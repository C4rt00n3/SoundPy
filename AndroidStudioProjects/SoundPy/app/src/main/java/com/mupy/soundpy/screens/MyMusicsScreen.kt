package com.mupy.soundpy.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mupy.soundpy.ContextMain
import com.mupy.soundpy.R
import com.mupy.soundpy.components.GenericCardMusic
import com.mupy.soundpy.database.Music
import com.mupy.soundpy.ui.theme.BrandColor
import com.mupy.soundpy.ui.theme.ColorWhite
import com.mupy.soundpy.ui.theme.SoundPyTheme

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun MyMusicsScreen(viewModel: ContextMain, navHostController: NavHostController, context: Context) {
    val myPlaylist by viewModel.myPlaylist.observeAsState(arrayOf())
    val sound by viewModel.soundPy.observeAsState(null)
    var array: MutableList<Music> by remember {
        mutableStateOf(mutableListOf())
    }

    fun remove(music: Music) {
        array = array.filter { it.id != music.id }.toMutableList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 9.dp)
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = stringResource(R.string.my_musics),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = ColorWhite,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        LazyColumn {
            if (array.isNotEmpty()) items(array.size) { int ->
                GenericCardMusic(
                    music = array[int],
                    viewModel = viewModel,
                    navHostController = navHostController,
                    context = context,
                ) { remove(it) }
            }
        }
        if (sound?.order == "DESC") array = myPlaylist[0].music.reversed().toMutableList()
        else if (sound?.order == "ASC") array = myPlaylist[0].music.toMutableList()
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
private fun MyMusicsScreenPreview() {
    val navController: NavHostController = rememberNavController()
    SoundPyTheme {
        MyMusicsScreen(ContextMain(LocalContext.current, null), navController, LocalContext.current)
    }
}