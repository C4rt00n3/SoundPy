package com.mupy.soundpy.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mupy.soundpy.ContextMain
import com.mupy.soundpy.R
import com.mupy.soundpy.models.PlayListData
import com.mupy.soundpy.ui.theme.ColorWhite
import com.mupy.soundpy.ui.theme.SoundPyTheme
import com.mupy.soundpy.ui.theme.WhiteTransparent

private val playlistData = PlayListData(
    "Sertanejo",
    "https://lh3.googleusercontent.com/18eW5KsqS0J0Q9pX3y6KqrfrsJB5-U_LMbEJ_s2SwGSeCm2Th_XMebciVMg8Upg372kTotCy8QSh6T4h=w544-h544-l90-rj",
    "RDCLAK5uy_k5faEHND0iXJljeASESqJ3A8UtRr2FL00",
    "Topnejo"
)

@Composable
fun CardNewRelease(
    playListData: PlayListData = playlistData,
    viewModel: ContextMain,
    navHostController: NavHostController,
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .pointerInput(Unit) {
            detectTapGestures(onDoubleTap = {
                viewModel.setPlaylisData(playListData)
                viewModel.fetchPlaylist(playListData.url)
                navHostController.navigate("playlist")
            })
        }
        .background(Color.Black, ShapeDefaults.Medium),
        horizontalArrangement = Arrangement.SpaceBetween) {
        ImageComponent(
            linkThumb = playListData.thumb,
            byteArray = null,
            contentScale = ContentScale.Companion.Crop,
            modifier = Modifier
                .width(130.dp)
                .height(145.dp)
                .background(WhiteTransparent, ShapeDefaults.Medium),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Column(modifier = Modifier.padding(top = 16.dp)) {
                Text(
                    text = playListData.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = ColorWhite,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = playListData.author,
                    fontWeight = FontWeight.W400,
                    fontSize = 12.sp,
                    color = ColorWhite,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = stringResource(
                            R.string.favoritar_playlist
                        ),
                        tint = ColorWhite
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.action_simple_play),
                        contentDescription = stringResource(
                            R.string.favoritar_playlist
                        ),
                        tint = ColorWhite
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun CardNewReleasePreview() {
    SoundPyTheme {
        //CardNewRelease(
        //    playlistData
        // )
    }
}
