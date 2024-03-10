package com.mupy.soundpy.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mupy.soundpy.R
import com.mupy.soundpy.models.PlayListData
import com.mupy.soundpy.ui.theme.TextColor2
import com.mupy.soundpy.ui.theme.WhiteTransparent

@Composable
fun PlaylistCard(
    playListData: PlayListData,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick()  },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        modifier = Modifier
            .width(300.dp)
            .height(300.dp),
        shape = RectangleShape
    ) {
        playListData.apply {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = thumb,
                    contentDescription = stringResource(R.string.imagem_da_playlist),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .background(WhiteTransparent)
                )
                Text(text = title, fontWeight = FontWeight.W400, color = TextColor2)
            }
        }
    }
}