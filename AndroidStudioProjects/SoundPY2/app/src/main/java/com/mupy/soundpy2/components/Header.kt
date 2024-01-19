package com.mupy.soundpy2.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mupy.soundpy2.ContextMain
import com.mupy.soundpy2.ui.theme.SoundPY2Theme
import com.mupy.soundpy2.ui.theme.TextColor2

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun Header(viewModel: ContextMain, navController: NavHostController) {
    val menu by viewModel.menu.observeAsState(false)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp, bottom = 38.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = { viewModel.setMenu(!menu) }, modifier = Modifier
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
        Search(viewModel, navController)
    }
}