package com.mupy.soundpy.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mupy.soundpy.ContextMain
import com.mupy.soundpy.R
import com.mupy.soundpy.components.ArtistCard
import com.mupy.soundpy.components.CardMusic
import com.mupy.soundpy.components.ImageCarousel
import com.mupy.soundpy.components.Title
import com.mupy.soundpy.database.Music
import com.mupy.soundpy.models.Artist
import com.mupy.soundpy.ui.theme.SoundPyTheme

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun Home(
    viewModel: ContextMain,
    navController: NavHostController,
    context: Context,
    padding: PaddingValues
) {
    val myPlaylist by viewModel.myPlaylist.observeAsState(arrayOf())
    val playListsCards by viewModel.playListsCards.observeAsState(arrayListOf())
    val soundPy by viewModel.soundPy.observeAsState(null)
    val music by viewModel.music.observeAsState(null)
    val artistPlaylist = mutableListOf(
        Artist(
            "Matue",
            "https://lh3.googleusercontent.com/rt-jCuxZpHLP4o_Uzr3GKjLLGyaURRY8Q9i5IXtHxhTWObFEm-9id2RiSsJtWYPdVF2RHWFeZThO2dJtDQ=w1400-h583-p-l90-rj",
            "UCYF3HLy2nzDREEE50KbOnKA"
        ),
        Artist(
            "Matue",
            "https://lh3.googleusercontent.com/rt-jCuxZpHLP4o_Uzr3GKjLLGyaURRY8Q9i5IXtHxhTWObFEm-9id2RiSsJtWYPdVF2RHWFeZThO2dJtDQ=w1400-h583-p-l90-rj",
            "UCYF3HLy2nzDREEE50KbOnKA"
        ),
        Artist(
            "Matue",
            "https://lh3.googleusercontent.com/rt-jCuxZpHLP4o_Uzr3GKjLLGyaURRY8Q9i5IXtHxhTWObFEm-9id2RiSsJtWYPdVF2RHWFeZThO2dJtDQ=w1400-h583-p-l90-rj",
            "UCYF3HLy2nzDREEE50KbOnKA"
        ),
        Artist(
            "Matue",
            "https://lh3.googleusercontent.com/rt-jCuxZpHLP4o_Uzr3GKjLLGyaURRY8Q9i5IXtHxhTWObFEm-9id2RiSsJtWYPdVF2RHWFeZThO2dJtDQ=w1400-h583-p-l90-rj",
            "UCYF3HLy2nzDREEE50KbOnKA"
        ),
    )
    var principalPlaylist by remember { mutableStateOf(arrayOf<Music>()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Title(stringResource(R.string.recenly_played))
        if (principalPlaylist.isNotEmpty()) LazyRow {
            items(principalPlaylist.size) { index ->
                CardMusic(music = principalPlaylist[index], onClick = {
                    if (it.url != music?.url) soundPy?.open(it)
                    navController.navigate("music")
                }) {
                    viewModel.deleteMusic(it) {
                        principalPlaylist =
                            principalPlaylist.filter { musicF -> musicF.url != principalPlaylist[index].url }
                                .toTypedArray()
                    }
                }
            }
        }
        ImageCarousel(playListsCards, navController, viewModel)
        Title(text = stringResource(R.string.your_favorite_artists))
        LazyRow {
            items(artistPlaylist.toList()) { artist ->
                ArtistCard(artist = artist)
            }
        }

        LaunchedEffect(Unit) {
            viewModel.getPlaylists()
        }

        if (myPlaylist.isNotEmpty()) principalPlaylist = myPlaylist[0].music.toTypedArray()
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    val navController: NavHostController = rememberNavController()
    SoundPyTheme {
        Home(
            ContextMain(LocalContext.current, null),
            navController,
            LocalContext.current,
            PaddingValues(0.dp)
        )
    }
}

/*
    val playlist by viewModel.playListsCards.observeAsState(mutableListOf())
    val myPlaylist by viewModel.myPlaylist.observeAsState(arrayOf())
    val soundPy by viewModel.soundPy.observeAsState(
        SoundPy(
            context,
            PlaylistWithMusic(MyPlaylists(0, null, "Carregando"), mutableListOf()),
            viewModel
        )
    )
    *//*if (playlist.isNotEmpty()) LazyRow {
            items(playlist.toList()) {
                PlaylistCard(playListData = it) {
                    viewModel.fetchPlaylist(it.link)
                    viewModel.setPlaylisData(it)
                    navController.navigate("playlist")
                }
            }
        }
     *//*
if (myPlaylist.isNotEmpty()) {
                val my = if (soundPy.order == "DESC") myPlaylist[0].music.reversed()
                else myPlaylist[0].music.toList()
                for (music in my) {
                    CardMusic(music = music) {
                        soundPy.player.pause()
                        viewModel.setCurrentPlaylist(null)
                        soundPy.open(it)
                        soundPy.player.play()
                        navController.navigate("music")
                    }
                }
            }
*/