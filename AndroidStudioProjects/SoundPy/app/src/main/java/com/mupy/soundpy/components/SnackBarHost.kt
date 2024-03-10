package com.mupy.soundpy.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mupy.soundpy.ContextMain
import com.mupy.soundpy.models.Menu
import com.mupy.soundpy.ui.theme.SoundPyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnackBarHost(
    viewModel: ContextMain,
    // navController: NavHostController
) {
    val sheetState = rememberModalBottomSheetState()
    val menu by viewModel.menu.observeAsState(Menu(false) @Composable {})

    if (menu.open) ModalBottomSheet(
        containerColor = Color.Black,
        onDismissRequest = {
            viewModel.setMenu(Menu(false) @Composable {})
        },
        sheetState = sheetState,
    ) {
        Column(modifier = Modifier.padding(all = 16.dp)) {
            menu.menu()
        }
        Spacer(modifier = Modifier.padding(bottom = 30.dp))
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
private fun SnackBarHostPreview() {
    // val navController: NavHostController = rememberNavController()
    SoundPyTheme {
        SnackBarHost(
            ContextMain(LocalContext.current, null),
            // navController
        )
    }
}