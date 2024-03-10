package com.mupy.soundpy.screens

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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mupy.soundpy.ContextMain
import com.mupy.soundpy.components.Header
import com.mupy.soundpy.components.Modal
import com.mupy.soundpy.components.Permission
import com.mupy.soundpy.components.Player
import com.mupy.soundpy.components.SnackBarHost
import com.mupy.soundpy.components.menus.BarMenu

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun Screens(
    navController: NavHostController, context: Context, viewModel: ContextMain
) {
    Scaffold(
        // floatingActionButton = { ToastComp(viewModel = viewModel) },
        topBar = {
            Header(
                viewModel = viewModel,
                navController,
                // context
            )
        },
        containerColor = Color.Black,
        contentColor = Color.Black,
        snackbarHost = {
            SnackBarHost(
                // navController = navController,
                viewModel = viewModel
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .padding(horizontal = 9.dp)
                    .background(Color.Black)
            ) {
                Player(viewModel = viewModel, navController = navController)
                BarMenu(
                    viewModel = viewModel, navController
                )
            }
        },
        content = { padding ->
            Modal(viewModel = viewModel)
            NavHost(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.Black)
                    .padding(),
                navController = navController,
                startDestination = "home"
            ) {
                composable("home") {
                    Home(viewModel, navController, context, padding)
                }
                composable("music") {
                    MusicScreen(viewModel, context)
                }
                composable("playlist") {
                    PlaylistScreen(
                        viewModel = viewModel, navController, context
                    )
                }
                composable("search") {
                    SearchScreen(
                        viewModel = viewModel, navHostController = navController, context = context
                    )
                }
                composable("favorites") {
                    MyMusicsScreen(
                        context = context, navHostController = navController, viewModel = viewModel
                    )
                }
            }
            Permission(
                viewModel = viewModel,
                // context = context
            )
        },
    )
}