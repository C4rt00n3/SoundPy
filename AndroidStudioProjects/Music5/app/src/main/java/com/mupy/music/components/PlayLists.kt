package com.mupy.music.components


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
import kotlinx.coroutines.launch

@Composable
fun PlayLists(
    titlePlaylist: String,
    viewModel: ContextMain,
    callback: (PlayListData) -> Unit
    ) {
    val listPlaylist: List<PlayListData> = listOf(
        PlayListData(
            "Sertanejo",
            "https://lh3.googleusercontent.com/18eW5KsqS0J0Q9pX3y6KqrfrsJB5-U_LMbEJ_s2SwGSeCm2Th_XMebciVMg8Upg372kTotCy8QSh6T4h=w544-h544-l90-rj",
            "https://music.youtube.com/playlist?list=RDCLAK5uy_k5faEHND0iXJljeASESqJ3A8UtRr2FL00",
            "Topnejo"
        ), PlayListData(
            "Trap",
            "https://lh3.googleusercontent.com/Nbv9b6PTaELOo14LJO90khFgsXf62QStHsJtpaV1P0yLJwcskFCaONFLdaXGQYH7e5-7iMfhsx4tIQ=w544-h544-l90-rj",
            "https://music.youtube.com/playlist?list=PLNyUJbuBiyw0SmnPYy4QfkDnpgq85fJBl",
            "TRAP BRASIL 2023"
        ), PlayListData(
            "Funk",
            "https://yt3.googleusercontent.com/mhawOLp1YtaUXhAyQnvGIqNWP9oQ9Ry7QaVXEd_ymnTAC4eZ0pVHOIX0HD5ZZuW6mj1r--rFqWNQ=s576",
            "https://music.youtube.com/playlist?list=RDCLAK5uy_nRxkdjoJYfKXKh4HyRtpuHKfmIfSH2khY",
            "Funk de Natal"
        ), PlayListData(
            "Rock",
            "https://lh3.googleusercontent.com/w8QDcpITg-64iylxia0Z4oWzbmlkHdSeSNyGslc_0ZcJgCtgLHkhugunsDRh_t87UQadn_si6-gPpvI=w544-h544-l90-rj",
            "https://music.youtube.com/playlist?list=RDCLAK5uy_nZiG9ehz_MQoWQxY5yElsLHCcG0tv9PRg",
            "Os maiores sucessos do rock clássico"
        ), PlayListData(
            "Pop",
            "https://lh3.googleusercontent.com/dWVVsBzSkEOACn-HlaxZSLWC3WfOXAnRu7PWTv-fmaDdjtbYXYIck8dqrRTccWZEkqvBlEVgf-3p4A=w544-h544-l90-rj",
            "https://music.youtube.com/playlist?list=RDCLAK5uy_k8QSlZPzi4R3781ftLrAKefTJJ6x7JrVA",
            "Os maiores êxitos pop"
        )
    )
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
        items(listPlaylist.size) {
            val background =
                ButtonDefaults.buttonColors(containerColor = Color.Transparent)

            Button(
                onClick = { callback(listPlaylist[it])
                    viewModel.viewModelScope.launch{
                        viewModel.fetchPlaylist(playListData.link)
                    }},
                colors = background,
                shape = RectangleShape,
                modifier = Modifier.padding(0.dp).width(280.dp).height(280.dp)

            ) {
                AsyncImage(
                    model = listPlaylist[it].thumb_url,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                )
            }
        }
    }
}