package com.mupy.soundpy.components.menus

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mupy.soundpy.ContextMain
import com.mupy.soundpy.R
import com.mupy.soundpy.ui.theme.ColorWhite

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun BarMenu(viewModel: ContextMain, navHostController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.Black),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        IconButton(onClick = { navHostController.navigate("home") }) {
            Icon(
                painter = painterResource(id = R.drawable.music),
                contentDescription = stringResource(
                    R.string.ir_para_pagina_home
                ),
                tint = ColorWhite,
            )
        }
        IconButton(onClick = { navHostController.navigate("search") }) {

            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = stringResource(R.string.ir_para_pagina_de_pesquisa),
                tint = ColorWhite,
            )
        }
        IconButton(onClick = { navHostController.navigate("favorites") }) {
            Icon(
                painter = painterResource(id = R.drawable.heart),
                contentDescription = stringResource(R.string.ir_para_musicas_baixadas),
                tint = ColorWhite,
            )
        }
    }
}
