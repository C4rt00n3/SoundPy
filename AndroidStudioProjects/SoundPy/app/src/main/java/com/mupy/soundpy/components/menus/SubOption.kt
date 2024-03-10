package com.mupy.soundpy.components.menus

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mupy.soundpy.R
import com.mupy.soundpy.database.MyPlaylists
import com.mupy.soundpy.database.PlaylistWithMusic
import com.mupy.soundpy.ui.theme.ColorWhite
import com.mupy.soundpy.utils.Utils

@Composable
fun SubOption(
    expand: Boolean,
    context: Context,
    myPlaylist: Array<PlaylistWithMusic>,
    onClickRemove: (PlaylistWithMusic) -> Unit,
    onClick: (MyPlaylists) -> Unit,
) {
    if (expand) {
        val colors = ButtonDefaults.buttonColors(Color.Transparent)
        for ((count, e) in myPlaylist.withIndex()) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .width((LocalConfiguration.current.screenWidthDp - 100).dp)
                        .weight(2f)
                        .pointerInput(Unit) {
                            detectTapGestures(onLongPress = {
                                if (count != 0) onClickRemove(e)
                                Toast
                                    .makeText(
                                        context,
                                        context.getText(R.string.playlist_apagada),
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            })
                        }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { /*TODO*/ },
                            shape = CircleShape,
                            colors = colors,
                            modifier = Modifier.size(30.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            if (e.music.isNotEmpty()) Utils().toBitmap(e.music.last().bitImage)
                                ?.let {
                                    Image(
                                        bitmap = it.asImageBitmap(),
                                        contentDescription = stringResource(id = R.string.imagem_da_playlist),
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop,
                                    )
                                }
                            else Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Red),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "!",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = ColorWhite
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = e.playlist.name.toString(), color = ColorWhite, fontSize = 14.sp
                        )
                    }
                }
                if (count != 0) IconButton(onClick = {
                    onClick(e.playlist)
                    Toast.makeText(
                        context, context.getText(R.string.musica_adicionada), Toast.LENGTH_SHORT
                    ).show()
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = stringResource(R.string.adiciona_na_playlist),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}