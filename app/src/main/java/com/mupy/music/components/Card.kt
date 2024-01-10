package com.mupy.music.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mupy.music.models.Music
import com.mupy.music.screen.ContextMain
import com.mupy.music.ui.theme.ColorWhite
import com.mupy.music.ui.theme.TextColor2
import com.mupy.music.ui.theme.WhiteTransparent
import com.mupy.music.utils.PlayerMusic
import com.mupy.music.utils.Utils
import java.io.File

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun Card(file: File, id: Int, navController: NavHostController, viewModel: ContextMain) {
    val utils = Utils()

    var enable by remember {
        mutableStateOf(true)
    }
    val music: Music = utils.setMusic(id, file.path).let {
        it ?: Music(0, "", "", "", "", "",null)
    }
    val musicView: Music? by viewModel.music.observeAsState(null)
    val player by viewModel.player.observeAsState(PlayerMusic(mutableListOf()))

    
    val background =
        ButtonDefaults.buttonColors(containerColor = if (music.url.isNotBlank()) Color.Transparent else WhiteTransparent)

    Button(
        modifier = Modifier
            .width(280.dp)
            .height(280.dp)
            .padding(end = 8.dp),
        onClick = {
            enable = false
            if(!player.isPlaying() && music.title == musicView?.title){
                player.start()
            }else {
                player.open(id)?.let { viewModel.setMusic(it) }
                player.start()
            }
            navController.navigate("music")
            viewModel.setPause(player.isPlaying())
            viewModel.startTime()
            enable = true
        },
        colors = background,
        enabled = enable,
        shape = RectangleShape
    ) {
        Column {
            if (music.bitImage != null) {
                Image(
                    bitmap = music.bitImage.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
            } else if(music.thumb.isNotBlank()) {
                AsyncImage(model = music.thumb, contentDescription = music.title, modifier = Modifier.fillMaxWidth())
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            Text(
                text = music.title,
                color = ColorWhite,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Text(
                text = music.author,
                color = TextColor2,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }

    }
}