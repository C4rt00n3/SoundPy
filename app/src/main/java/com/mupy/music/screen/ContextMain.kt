package com.mupy.music.screen

import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mupy.music.models.Music
import com.mupy.music.models.PlayListData
import com.mupy.music.network.ApiViewModel
import com.mupy.music.utils.Utils
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

class ContextMain : ApiViewModel() {
    private val utils = Utils()

    private val _mediaPlayer = MutableLiveData(MediaPlayer())
    val mediaPlayer: LiveData<MediaPlayer> = _mediaPlayer

    private val _pause = MutableLiveData<Boolean>()
    val pause: LiveData<Boolean> = _pause

    private val _musics = MutableLiveData<MutableList<File>>()
    val musics: LiveData<MutableList<File>> = _musics

    private val _music = MutableLiveData(Music(0, "", "", "", ""))
    val music: LiveData<Music> = _music

    private var _playlist = MutableLiveData<PlayListData>()
    val playlist: LiveData<PlayListData> = _playlist

    private var _mark = MutableLiveData<Boolean>(false)
    var mark: LiveData<Boolean> = _mark

    private val _mude = MutableLiveData(false)
    val mude: LiveData<Boolean> = _mude

    val currentPosition = MutableLiveData<Int>()

    init {
        _playlist.value = PlayListData("", "", "", "")
        _musics.value = utils.getMusicFiles { }
        _music.value =
            if (musics.value?.size != 0) utils.setMusic(0, _musics.value!![0].path) else Music(
                0, "", "", "", ""
            )
        _mediaPlayer.value = MediaPlayer()
        _pause.value = mediaPlayer.value?.isPlaying
    }

    fun getFiles() {
        viewModelScope.launch {
            _musics.value = utils.getMusicFiles { _musics.value = mutableListOf() }
        }
    }

    // Função para atualizar o valor de pause
    fun setPause(boolean: Boolean) {
        viewModelScope.launch { _pause.value = boolean }
    }

    fun startTime() {
        viewModelScope.launch {
            while (true) {
                currentPosition.value = mediaPlayer.value?.currentPosition
                delay(1000) // atraso por um segundo
            }
        }
    }

    fun setMude(boolean: Boolean){
        _mude.value = boolean
    }

    fun setMark(boolean: Boolean) {
        _mark.value = boolean
    }

    fun linearProgession() {
        setMediaPlayer()
        utils.moveMusic(
            "next", musics.value!!, music.value!!, mediaPlayer.value!!
        ) { setMusic(it) }
        setPause(mediaPlayer.value!!.isPlaying)
        startTime()
    }

    // Função para atualizar o valor de mediaPlayer
    fun setMediaPlayer() {
        viewModelScope.launch {
            mediaPlayer.value?.stop()
            _mediaPlayer.value = null
            _mediaPlayer.value = MediaPlayer()
        }
    }

    // Função para atualizar o valor de musics
    fun setMusics(newMusics: MutableList<File>) {
        viewModelScope.launch { _musics.value = newMusics }
    }

    // Função para atualizar o valor de music
    fun setMusic(newMusic: Music) {
        viewModelScope.launch {
            _music.value = newMusic
        }
    }

    // Função para atualizar o valor de playlist
    fun setPlaylist(newPlaylist: PlayListData) {
        viewModelScope.launch { _playlist.value = newPlaylist }
    }
}