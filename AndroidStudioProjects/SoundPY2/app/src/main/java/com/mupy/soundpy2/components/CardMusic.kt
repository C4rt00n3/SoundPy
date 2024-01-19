package com.mupy.soundpy2.components

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mupy.soundpy2.ContextMain
import com.mupy.soundpy2.models.Music
import com.mupy.soundpy2.ui.theme.SoundPY2Theme
import com.mupy.soundpy2.ui.theme.TextColor2
import com.mupy.soundpy2.ui.theme.WhiteTransparent
import com.mupy.soundpy2.utils.Play

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun CardMusic(
    music: Music,
    id: Int,
    context: Context,
    navHostController: NavHostController?,
    viewModel: ContextMain,
) {
    Button(
        onClick = {
            viewModel.setMusic(music)
            viewModel.setPlayer()
            viewModel.open(id)
            navHostController?.navigate("music")
            viewModel.setOnCompletionListener()
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        shape = RectangleShape
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (music.bitImage == null) AsyncImage(
                model = music.thumb,
                contentDescription = "Imagem da musica ${music.title}",
                modifier = Modifier
                    .size(240.dp)
                    .background(WhiteTransparent),
                contentScale = ContentScale.Crop
            )
            else Image(
                bitmap = music.bitImage.asImageBitmap(),
                contentDescription = "Imagem da musica ${music.title}",
                modifier = Modifier
                    .size(240.dp)
                    .background(WhiteTransparent),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = music.title,
                fontWeight = FontWeight(500),
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier
                    .widthIn(max = 200.dp)
                    .padding(top = 9.dp),
                maxLines = 1
            )
            Text(
                text = music.author,
                fontWeight = FontWeight(400),
                fontSize = 16.sp,
                color = TextColor2,
                modifier = Modifier.widthIn(max = 200.dp),
                maxLines = 1
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Preview(showBackground = true)
@Composable
fun CardMusicPreview() {
    SoundPY2Theme {
        CardMusic(Music(0, "", "", "", "", "", null), 0, LocalContext.current, null, ContextMain())
    }
}
