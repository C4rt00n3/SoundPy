package com.mupy.soundpy.components.menus

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.mupy.soundpy.ContextMain
import com.mupy.soundpy.R
import com.mupy.soundpy.models.Menu
import kotlinx.coroutines.launch

@Composable
fun MainMenu(navHostController: NavHostController, context: Context, viewModel: ContextMain) {
    var expand by remember { mutableStateOf(false) }
    val myPlaylist by viewModel.myPlaylist.observeAsState(null)

    Option(
        onClick = { navHostController.navigate("home") },
        text = stringResource(R.string.home),
        contentDescription = "Ir para a home",
        painter = painterResource(id = R.drawable.baseline_home_24),
        expand = null,
    )
    Option(
        onClick = {
            navHostController.navigate("favorites")
            viewModel.setMenu(Menu(false) {})
        },
        text = stringResource(id = R.string.minhas_musicas),
        contentDescription = stringResource(R.string.ir_para_as_minhas_musicas),
        painter = painterResource(id = R.drawable.baseline_library_music_24),
        expand = null,
    )
    Option(
        onClick = {
            expand = !expand
        },
        text = stringResource(R.string.minhas_playlists),
        contentDescription = stringResource(R.string.ir_para_minhas_playlists),
        painter = painterResource(id = R.drawable.list),
    ) {
        myPlaylist?.let {
            SubOption(
                expand = expand, context = context, myPlaylist = it,
                onClickRemove = {
                    viewModel.viewModelScope.launch {
                        viewModel.removeElementMyPlaylist(it)
                    }
                },
            ) {}
        }
    }
}
