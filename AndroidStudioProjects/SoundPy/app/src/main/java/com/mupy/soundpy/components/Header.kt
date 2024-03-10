package com.mupy.soundpy.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mupy.soundpy.ContextMain
import com.mupy.soundpy.R
import com.mupy.soundpy.ui.theme.ColorWhite
import com.mupy.soundpy.ui.theme.SoundPyTheme

@Composable
fun Header(
    viewModel: ContextMain,
    navHostController: NavHostController,
) {
    val currentBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route ?: ""

    if (currentRoute != "playlist") Row(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {
            /*viewModel.setMenu(Menu(true) @Composable {
                MainMenu(
                    navHostController = navHostController, viewModel = viewModel, context = context
                )
            })*/
        }) {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                contentDescription = stringResource(R.string.abrir_menu),
                modifier = Modifier.size(50.dp)
            )
        }
        if (currentRoute != "search") IconButton(onClick = {
            /*viewModel.setMenu(Menu(true) @Composable {
                MainMenu(
                    navHostController = navHostController, viewModel = viewModel, context = context
                )
            })*/
        }) {
            Icon(
                painter = painterResource(id = R.drawable.more_vertical),
                contentDescription = stringResource(R.string.abrir_menu),
                modifier = Modifier.size(50.dp),
                tint = ColorWhite
            )
        }
        else SearchInput(viewModel, navHostController)
    }
}

@Preview(showBackground = true)
@Composable
private fun HeaderPreview() {
    val navController: NavHostController = rememberNavController()
    SoundPyTheme {
        Header(
            ContextMain(LocalContext.current, null), navController,
        )
    }
}