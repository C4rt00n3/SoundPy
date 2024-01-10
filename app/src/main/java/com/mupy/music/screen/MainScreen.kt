package com.mupy.music.screen

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mupy.music.components.AppBar
import com.mupy.music.components.ContentMenu
import com.mupy.music.components.Permission
import com.mupy.music.components.Player
import com.mupy.music.ui.theme.BrandColor
import androidx.lifecycle.viewmodel.compose.viewModel as rememberViewModel

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun MainScreen(
    navController: NavHostController, context: Context, viewModel: ContextMain = rememberViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(BrandColor)
            .verticalScroll(ScrollState(0)),
    ) {
        Permission(viewModel)
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen(navController, viewModel)
            }
            composable("music") {
                MusicScreen(navController, viewModel)
            }
            composable("playlist") {
                PlaylistScreen(navController, context, viewModel)
            }
            composable("search") {
                SearchScreen(navController, context, viewModel)
            }
            composable("favorites") {
                BaixadosScreen(
                    context = context, navHostController = navController, viewModel = viewModel
                )
            }
            Toast.makeText(context, "Calma calabreso", Toast.LENGTH_SHORT).show()
        }
    }

    val displayMetrics = DisplayMetrics()
    LocalContext.current.display!!.getRealMetrics(displayMetrics)
    val height = (displayMetrics.heightPixels / LocalDensity.current.density).toInt()

    AppBar(viewModel = viewModel, navController = navController)
    ContentMenu(navController, viewModel = viewModel)
    Scaffold(
        modifier = Modifier
            .absoluteOffset(y = (height - 160).dp)
            .height(80.dp),
        containerColor = BrandColor,
        content = { Player(viewModel = viewModel, padding = it, navController) },
        // topBar = { Player(viewModel = viewModel, padding = PaddingValues(), navController) }
    )
}
