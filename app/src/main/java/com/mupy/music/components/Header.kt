package com.mupy.music.components

import android.os.Build
import android.util.DisplayMetrics
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mupy.music.R
import com.mupy.music.screen.ContextMain
import com.mupy.music.ui.theme.ColorWhite
import com.mupy.music.ui.theme.TextColor2
import com.mupy.music.ui.theme.WhiteTransparent

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun Header(navController: NavHostController, viewModel: ContextMain) {
    val colors = TextFieldDefaults.textFieldColors(
        textColor = ColorWhite,
        containerColor = WhiteTransparent,
        cursorColor = TextColor2,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
    )
    val search = remember { mutableStateOf("") }
    // val loading = remember { mutableStateOf(false) }
    val searchYoutube by viewModel.searchYoutube.observeAsState()

    val displayMetrics = DisplayMetrics()
    LocalContext.current.display!!.getRealMetrics(displayMetrics)
    val width = (displayMetrics.widthPixels / LocalDensity.current.density).toInt()

    TextField(
        value = search.value.replace(Regex("/\r?\n|\r/g"), ""),
        onValueChange = {
            try {
                // loading.value = true
                search.value = it.replace(Regex("/\r?\n|\r/g"), "")
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                // loading.value = false
            }
        },
        modifier = Modifier
            .height(60.dp)
            .width((width - 90).dp)
            .padding(0.dp)
            .absoluteOffset(y = 0.dp),
        colors = colors,
        shape = RoundedCornerShape(50.dp),
        placeholder = {
            Text(
                text = stringResource(R.string.pesquisa),
                color = TextColor2,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxHeight()
            )
        },
        maxLines = 1,
        trailingIcon = {
            IconButton(onClick = {
                viewModel.searchYoutube(search.value)
                navController.navigate("search")
            }) {
                Icon(
                    Icons.Filled.Search,
                    modifier = Modifier.width(60.dp),
                    tint = TextColor2,
                    contentDescription = stringResource(R.string.pesquisar_use)
                )
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
        )
    )
}
