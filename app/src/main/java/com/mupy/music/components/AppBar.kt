package com.mupy.music.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mupy.music.screen.ContextMain
import com.mupy.music.ui.theme.BrandColor
import com.mupy.music.ui.theme.TextColor2

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun AppBar(navController: NavHostController,viewModel: ContextMain){
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(
                onClick = { viewModel.setMenu(true) },
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .padding(horizontal = 9.dp)
            ) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = null,
                    tint = TextColor2,
                )
            }
        },
        actions = {
            Header(viewModel=viewModel,navController = navController)
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = BrandColor)
    )
}