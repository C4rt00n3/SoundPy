package com.mupy.music

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.mupy.music.screen.MainScreen
import com.mupy.music.ui.theme.MusicTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MusicTheme {
                LayoutMain()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}

@Composable
private fun LayoutMain() {
    val navController = rememberNavController()
    MainScreen(navController)
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MusicTheme {
        LayoutMain()
    }
}