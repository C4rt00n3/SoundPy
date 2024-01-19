package com.mupy.soundpy2.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mupy.soundpy2.ContextMain
import com.mupy.soundpy2.R
import com.mupy.soundpy2.components.CardDefault
import com.mupy.soundpy2.components.Header
import com.mupy.soundpy2.models.Music
import com.mupy.soundpy2.ui.theme.BrandColor
import com.mupy.soundpy2.ui.theme.ColorWhite

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun MyMusics(viewModel: ContextMain, context: Context, navHostController: NavHostController) {
    val musics: MutableList<Music> by viewModel.musics.observeAsState(mutableListOf())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(BrandColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.my_musics),
                fontSize = 24.sp,
                fontWeight = FontWeight(500),
                color = ColorWhite,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                textAlign = TextAlign.Center
            )
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(musics.size) {
                CardDefault(
                    item = musics[it], context = context, viewModel = viewModel, index = it
                )
            }
        }
    }
}