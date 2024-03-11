package com.mupy.soundpy.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.mupy.soundpy.R
import com.mupy.soundpy.utils.Utils

@Composable
fun ImageComponent(
    linkThumb: String,
    byteArray: ByteArray?,
    modifier: Modifier,
    contentDescription: String = "",
    preview: Boolean = false,
    contentScale: ContentScale = ContentScale.Companion.Crop,
) {
    val utils = Utils()
    if (preview) Image(
        painter = painterResource(id = R.mipmap.deadpoll),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    )
    else if (byteArray == null && linkThumb.isNotBlank()) AsyncImage(
        model = linkThumb,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    )
    else if(linkThumb.isBlank() && byteArray == null)
        Image(
            painter = painterResource(id = R.mipmap.deadpoll),
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier
        )
    else utils.toBitmap(byteArray)?.let {
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier
        )
    }
}