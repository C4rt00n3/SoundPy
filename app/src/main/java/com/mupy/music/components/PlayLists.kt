package com.mupy.music.components


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import com.mupy.music.models.PlayListData
import com.mupy.music.screen.ContextMain
import com.mupy.music.ui.theme.ColorWhite
import com.mupy.music.ui.theme.TextColor2
import com.mupy.music.utils.PlayListUtils
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun PlayLists(
    titlePlaylist: String,
    viewModel: ContextMain,
    callback: (PlayListData) -> Unit
) {
    val playListUtils = PlayListUtils()
    val playListData: PlayListData by viewModel.playlist.observeAsState(
        PlayListData(
            "", "", "", ""
        )
    )
    Text(
        text = titlePlaylist,
        fontSize = 24.sp,
        fontWeight = FontWeight(600),
        color = ColorWhite,
        modifier = Modifier.padding(vertical = 16.dp)
    )
    LazyRow {
        items(playListUtils.listPlaylist.size) {
            val background =
                ButtonDefaults.buttonColors(containerColor = Color.Transparent)

            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = {
                        callback(playListUtils.listPlaylist[it])
                        viewModel.viewModelScope.launch {
                            viewModel.fetchPlaylist(playListData.link)
                        }
                    },
                    colors = background,
                    shape = RectangleShape,
                    modifier = Modifier
                        .padding(0.dp)
                        .width(280.dp)
                        .height(280.dp)

                ) {
                    AsyncImage(
                        model = playListUtils.listPlaylist[it].thumb_url,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                    )
                }
                Text(text = playListUtils.listPlaylist[it].title, fontSize = 18.sp, color = ColorWhite, fontWeight = FontWeight(700))
                Text(text = playListUtils.listPlaylist[it].genere, fontSize = 14.sp, color = TextColor2, fontWeight = FontWeight(400))
            }
        }
    }
}