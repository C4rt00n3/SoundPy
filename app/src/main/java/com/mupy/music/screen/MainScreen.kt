package com.mupy.music.screen

import android.os.Build
import android.util.DisplayMetrics
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mupy.music.components.Permission
import com.mupy.music.components.Player
import com.mupy.music.ui.theme.BrandColor

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun MainScreen(navController: NavHostController, viewModel: ContextMain = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(BrandColor)
            .verticalScroll(ScrollState(0)), verticalArrangement = Arrangement.SpaceBetween
    ) {
        Permission()
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen(navController, viewModel)
            }
            composable("music") {
                MusicScreen(navController, viewModel)
            }
            composable("playlist") {
                PlaylistScreen(viewModel)
            }
            composable("search") {}
        }
    }

    val displayMetrics = DisplayMetrics()
    LocalContext.current.display!!.getRealMetrics(displayMetrics)
    val height = (displayMetrics.heightPixels / LocalDensity.current.density).toInt()



    FloatingActionButton(
        onClick = {},
        modifier = Modifier
            .absoluteOffset(y = (height - 160).dp)
            .fillMaxWidth()
            .height(80.dp),
        shape = RectangleShape,
        content = { Player(viewModel = viewModel, padding = PaddingValues(), navController) })
}
