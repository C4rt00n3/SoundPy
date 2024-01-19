package com.mupy.music.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mupy.music.R
import com.mupy.music.ui.theme.ColorWhite

@Composable
fun Header(nav: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp), Arrangement.SpaceBetween
    ) {
        // val background = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        val modifierImage = Modifier
            .width(40.dp)
            .height(40.dp)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_menu_24),
                contentDescription = "Botão de menu",
                modifier = modifierImage,
                tint = ColorWhite,
            )
        }
        IconButton(onClick = { nav() }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = "Buotão de pesquisa",
                modifier = modifierImage,
                tint = ColorWhite,
            )
        }
    }
}
