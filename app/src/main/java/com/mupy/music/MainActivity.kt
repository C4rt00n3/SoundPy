    package com.mupy.music

    import android.content.Context
    import android.os.Build
    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.annotation.RequiresApi
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.platform.LocalContext
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.navigation.compose.rememberNavController
    import com.mupy.music.screen.MainScreen
    import com.mupy.music.ui.theme.MusicTheme

    class MainActivity : ComponentActivity() {
        @RequiresApi(Build.VERSION_CODES.R)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                MusicTheme {
                    LayoutMain(this)
                }
            }
        }

        override fun onRequestPermissionsResult(
            requestCode: Int, permissions: Array<String?>, grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @Composable
    private fun LayoutMain(context: Context) {
        val navController = rememberNavController()
        MainScreen(context = context, navController = navController)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @Preview(showBackground = true)
    @Composable
    fun MainPreview() {
        MusicTheme {
            LayoutMain(LocalContext.current)
        }
    }