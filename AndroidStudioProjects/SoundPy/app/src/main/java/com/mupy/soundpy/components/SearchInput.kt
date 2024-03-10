package com.mupy.soundpy.components

import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mupy.soundpy.ContextMain
import com.mupy.soundpy.R
import com.mupy.soundpy.ui.theme.TextColor2
import com.mupy.soundpy.ui.theme.WhiteTransparent

@Composable
fun SearchInput(
    viewModel: ContextMain, navController: NavHostController
) {
    val searchInput by viewModel.searchInput.observeAsState("")
    val width = LocalConfiguration.current.screenWidthDp
    val colors = TextFieldDefaults.colors(
        focusedContainerColor = WhiteTransparent,
        unfocusedContainerColor = WhiteTransparent,
        disabledContainerColor = WhiteTransparent,
        cursorColor = TextColor2,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
    )

    fun confirme() {
        viewModel.searchYoutube(searchInput)
        // navController.navigate("search")
    }

    TextField(
        value = searchInput,
        onValueChange = {
            viewModel.setSearchInput(it)
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
            IconButton(onClick = { confirme() }) {
                Icon(
                    Icons.Filled.Search,
                    modifier = Modifier.width(60.dp),
                    tint = TextColor2,
                    contentDescription = stringResource(R.string.iniciar_busa)
                )
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { confirme() }),
        textStyle = TextStyle(color = TextColor2.copy(1f))
    )
}
