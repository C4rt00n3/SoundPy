package com.mupy.soundpy2.components

import android.os.Build
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mupy.soundpy2.ContextMain
import com.mupy.soundpy2.R
import com.mupy.soundpy2.ui.theme.TextColor2
import com.mupy.soundpy2.ui.theme.WhiteTransparent

@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(viewModel: ContextMain, navController: NavHostController) {
    val search by viewModel.searchInput.observeAsState("")

    val colors = TextFieldDefaults.textFieldColors(
        containerColor = WhiteTransparent,
        cursorColor = TextColor2,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
    )
    val width = LocalConfiguration.current.screenWidthDp

    TextField(
        value = search,
        onValueChange = {
            try {
                viewModel.setSearch(it.replace(Regex("/\r?\n|\r/g"), ""))
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
                text = stringResource(R.string.procure_sua_musica),
                color = TextColor2,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxHeight()
            )
        },
        maxLines = 1,
        trailingIcon = {
            IconButton(onClick = {
                if (search.isNotBlank())
                    navController.navigate("search")
                viewModel.searchYoutube(search)
            }) {
                Icon(
                    Icons.Filled.Search,
                    modifier = Modifier.width(60.dp),
                    tint = TextColor2,
                    contentDescription = stringResource(R.string.buscar_musica)
                )
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        textStyle = TextStyle(color = TextColor2),
    )
}