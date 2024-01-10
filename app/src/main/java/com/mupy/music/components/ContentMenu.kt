package com.mupy.music.components

import android.os.Build
import android.util.DisplayMetrics
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.mupy.music.R
import com.mupy.music.screen.ContextMain
import com.mupy.music.ui.theme.BrandColor
import com.mupy.music.ui.theme.TextColor2
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun ContentMenu(navHostController: NavHostController, viewModel: ContextMain) {
    val displayMetrics = DisplayMetrics()
    LocalContext.current.display!!.getRealMetrics(displayMetrics)
    val width = (displayMetrics.widthPixels / LocalDensity.current.density).toInt()
    val menu: Boolean by viewModel.menu.observeAsState(false)

    if (menu) Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xB2091227))
    ) {
        Scaffold(modifier = Modifier.width((width - 150).dp),
            containerColor = BrandColor,
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .absoluteOffset(y = 0.dp)
                        .padding(it)
                ) {
                    IconButton(onClick = { viewModel.setMenu(false) }) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "Fechar menu",
                            tint = TextColor2
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 32.dp)
                            .fillMaxWidth()
                    ) {
                        Button(
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            onClick = {
                                viewModel.viewModelScope.launch {
                                    navHostController.navigate("home")
                                    viewModel.setMenu(false)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .padding(all = 0.dp)
                                .align(Alignment.Start)
                                .clip(RectangleShape)
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
                                .padding(all = 0.dp)
                                .align(Alignment.CenterHorizontally)
                                .clip(RectangleShape)
                        ) {
                            Row(
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
                                viewModel.viewModelScope.launch {
                                    navHostController.navigate("favorites")
                                    viewModel.setMenu(false)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .padding(all = 0.dp)
                                .align(Alignment.CenterHorizontally)
                                .clip(RectangleShape)
                        ) {
                            Row(
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
                    }
                }
            })
    }
}