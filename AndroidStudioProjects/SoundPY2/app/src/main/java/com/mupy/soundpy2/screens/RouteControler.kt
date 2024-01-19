package com.mupy.soundpy2.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mupy.soundpy2.ContextMain
import com.mupy.soundpy2.components.ContentMenu
import com.mupy.soundpy2.components.Header
import com.mupy.soundpy2.components.Permission
import com.mupy.soundpy2.components.Player
import com.mupy.soundpy2.components.ToastComp
import com.mupy.soundpy2.ui.theme.BrandColor
import com.mupy.soundpy2.ui.theme.SoundPY2Theme
import androidx.lifecycle.viewmodel.compose.viewModel as rememberViewModel

@OptIn(ExperimentalFoundationApi::class)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun RouteControler(
    navController: NavHostController, context: Context, viewModel: ContextMain = rememberViewModel()
) {
    val musics by viewModel.musics.observeAsState(mutableListOf())
    val toast by viewModel.toast.observeAsState(null)
    val count by viewModel.count.observeAsState(0)
    val pageState = rememberPagerState(initialPage = count) {
        musics.size
    }
    viewModel.setPageState(pageState)
    Permission(viewModel = viewModel)
    Scaffold(
        floatingActionButton = { ToastComp(viewModel = viewModel)},
        containerColor = BrandColor,
        topBar = {
            Header(viewModel = viewModel, navController)
        },
        snackbarHost = {
            ContentMenu(navHostController = navController, viewModel = viewModel)
        },
        bottomBar = {
            Player(
                viewModel = viewModel, padding = PaddingValues(), navController
            )
        },
        content = { pading ->
            NavHost(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(BrandColor)
                    .padding(pading), navController = navController, startDestination = "home"
            ) {
                composable("home") {
                    Home(navController, context, viewModel)
                }
                composable("music") {
                    MusicScreen(viewModel)
                }
                composable("playlist") {
                    PlaylistList(
                        viewModel = viewModel, context = context, navController = navController
                    )
                }
                composable("search") {
                    SearchScreen(
                        navController = navController,
                        context = context,
                        viewModel = viewModel
                    )
                }
                composable("favorites") {
                    MyMusics(
                        context = context, navHostController = navController, viewModel = viewModel
                    )
                }
            }
        },
    )
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@RequiresApi(Build.VERSION_CODES.R)
@Preview(showBackground = true)
@Composable
fun RouteControlerPreview() {
    SoundPY2Theme {
        val context = LocalContext.current
        RouteControler(NavHostController(context), context)
    }
}