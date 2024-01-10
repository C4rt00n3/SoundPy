package com.mupy.music.screen

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mupy.music.R
import com.mupy.music.components.CardMusic
import com.mupy.music.ui.theme.BrandColor
import com.mupy.music.ui.theme.TextColor2

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun SearchScreen(navHostController: NavHostController, context: Context, viewModel: ContextMain) {
    val searchYoutube by viewModel.searchYoutube.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(BrandColor)
            .padding(top = 140.dp)
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Resultados",
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color = TextColor2
                )
            }
            if (searchYoutube?.results?.isEmpty() == true) {
                Text(
                    stringResource(R.string.procure_por_algo),
                    color = TextColor2,
                    fontWeight = FontWeight(700),
                    fontSize = 28.sp
                )
            } else {
                searchYoutube?.results?.map { music ->
                    CardMusic(
                        music = music,
                        navController = navHostController,
                        context = context,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}
