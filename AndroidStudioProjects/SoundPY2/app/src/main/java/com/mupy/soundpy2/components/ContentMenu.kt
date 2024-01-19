package com.mupy.soundpy2.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mupy.soundpy2.ContextMain
import com.mupy.soundpy2.R
import com.mupy.soundpy2.ui.theme.BrandColor
import com.mupy.soundpy2.ui.theme.TextColor2

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun ContentMenu(navHostController: NavHostController, viewModel: ContextMain) {
    val menu: Boolean by viewModel.menu.observeAsState(false)

    val sheetState = rememberModalBottomSheetState()

    if (menu) ModalBottomSheet(
        containerColor = BrandColor,
        onDismissRequest = {
            viewModel.setMenu(false)
        },
        sheetState = sheetState,
    ) {
        // Sheet content
        Button(
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            onClick = {
                navHostController.navigate("home")
                viewModel.setMenu(false)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.Start)
                .clip(RectangleShape),
            contentPadding = PaddingValues(horizontal = 9.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home",
                    tint = TextColor2,
                    modifier = Modifier.padding(end = 16.dp)
                )
                Text(text = "Home")
            }
        }
        Button(
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RectangleShape),
            contentPadding = PaddingValues(horizontal = 9.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favoritos",
                    tint = TextColor2,
                    modifier = Modifier.padding(end = 16.dp)

                )
                Text(text = "Favoritos")
            }
        }
        Button(
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            onClick = {
                navHostController.navigate("favorites")
                viewModel.setMenu(false)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RectangleShape),
            contentPadding = PaddingValues(end = 9.dp, start = 9.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
            ) {
                Icon(
                    modifier = Modifier.padding(end = 16.dp),
                    painter = painterResource(id = R.drawable.baseline_downloading_24),
                    contentDescription = "Baixados",
                    tint = TextColor2
                )
                Text(text = "Baixados")
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}