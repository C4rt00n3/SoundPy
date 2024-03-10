package com.mupy.soundpy

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mupy.soundpy.database.AppDatabase
import com.mupy.soundpy.screens.Screens
import com.mupy.soundpy.ui.theme.SoundPyTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme
            val navController: NavHostController = rememberNavController()
            val systemUiController = rememberSystemUiController()

            systemUiController.setSystemBarsColor(
                color = Color.Black
            )

            val dataBase = Room.databaseBuilder(
                this, AppDatabase::class.java, "myPlaylists"
            ).fallbackToDestructiveMigration().build()

            SoundPyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Screens(
                        navController = navController,
                        context = this,
                        viewModel = ContextMain(this, dataBase)
                    )
                }
            }
        }
    }
}
