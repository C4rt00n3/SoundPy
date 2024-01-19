package com.mupy.music.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mupy.music.components.Header
import com.mupy.music.components.MyMusics
import com.mupy.music.components.PlayLists

@Composable
fun HomeScreen(navController: NavHostController, viewModel: ContextMain) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 16.dp)
    ) {
        Header { navController.navigate("search") }
        PlayLists("Recommended for you", viewModel) {
            viewModel.setPlaylist(it)
            navController.navigate("playlist")
        }

        MyMusics(navController,viewModel)
    }
}