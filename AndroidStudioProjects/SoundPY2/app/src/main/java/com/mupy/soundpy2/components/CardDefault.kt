package com.mupy.soundpy2.components

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import com.mupy.soundpy2.ContextMain
import com.mupy.soundpy2.models.Music
import com.mupy.soundpy2.ui.theme.ColorWhite
import com.mupy.soundpy2.ui.theme.TextColor2
import com.mupy.soundpy2.ui.theme.TrackColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun CardDefault(item: Music, context: Context, viewModel: ContextMain, index: Int) {
    var status by remember {
        mutableIntStateOf(if (index != -1) 1 else 0)
    }
    var progressDownload by remember {
        mutableFloatStateOf(0f)
    }
    val musicsFile by viewModel.musicsFile.observeAsState(mutableListOf())

    val icon = if (status == 0) Icons.Filled.Add else Icons.Filled.Close

    fun time() {
        viewModel.viewModelScope.launch {
            while (status == -1) {
                progressDownload++
                if (progressDownload == 100f) progressDownload = 0f
                delay(50.toLong())
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 9.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {
                println(index)
                if (index != -1) {
                    viewModel.open(index)
                    viewModel.setMusic(item)
                } else {
                    viewModel.download(item.url, item.title, context, false, item)
                }
            },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(0.dp)
        ) {
            Row {
                if (item.bitImage != null)
                    Image(
                        bitmap = item.bitImage.asImageBitmap(),
                        contentDescription = "Imagem da musica ${item.title}",
                        modifier = Modifier.size(80.dp),
                    )
                else AsyncImage(
                    model = item.thumb,
                    contentDescription = "Imagem da musica ${item.title}",
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .widthIn(180.dp, 280.dp)
                        .padding(start = 18.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = item.title,
                        color = ColorWhite,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        maxLines = 1
                    )
                    Text(
                        text = item.author,
                        color = TextColor2,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        maxLines = 1
                    )
                }
            }
        }

        IconButton(
            onClick = {
                println(status)
                viewModel.apply {
                    if (status == 0) {
                        status = -1
                        time()
                        try {
                            download(item.url, item.title, context, true, item)
                        } catch (err: Exception) {
                            err.printStackTrace()
                        } finally {
                            status = 1
                        }
                    } else {
                        musicsFile[index].let {
                            viewModel.delet(it, context)
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.CenterVertically),
        ) {
            if (status != -1) Icon(
                icon,
                contentDescription = "Baixar há musica ${item.title}.",
                modifier = Modifier.size(30.dp),
                tint = TextColor2
            )
            else CircularProgressIndicator(
                progress = progressDownload / 100f,
                trackColor = TrackColor,
                color = TextColor2,
                modifier = Modifier.size(30.dp),
                strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth,
                strokeCap = ProgressIndicatorDefaults.CircularIndeterminateStrokeCap,
            )
        }
    }
}
