package com.mupy.music.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mupy.music.R
import com.mupy.music.components.MyMusics
import com.mupy.music.components.PlayLists

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: ContextMain) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(end = 16.dp, start = 16.dp, top=90.dp)
    ) {
        PlayLists(stringResource(R.string.recommended_for_you), viewModel) {
            viewModel.setPlaylist(it)
            navController.navigate("playlist")
        }
        MyMusics(navController, viewModel)
    }
}
