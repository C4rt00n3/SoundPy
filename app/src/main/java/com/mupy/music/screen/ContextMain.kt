package com.mupy.music.screen

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mupy.music.models.Music
import com.mupy.music.models.PlayListData
import com.mupy.music.network.ApiRepository
import com.mupy.music.network.ApiViewModel
import com.mupy.music.utils.PlayerMusic
import com.mupy.music.utils.Utils
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

@RequiresApi(Build.VERSION_CODES.P)
class ContextMain : ApiViewModel() {
    private var repository = ApiRepository()

    private val utils = Utils()

    private val _pause = MutableLiveData(false)
    val pause: LiveData<Boolean> = _pause

    private val _musicsFile = MutableLiveData<MutableList<File>>()
    val musicsFile: LiveData<MutableList<File>> = _musicsFile


    private val _musics = MutableLiveData<MutableList<Music>>(mutableListOf())
    val musics: LiveData<MutableList<Music>> = _musics

    private val _player = MutableLiveData(_musicsFile.value?.let { it ->
        if (it.isNotEmpty()) PlayerMusic(mutableListOf())
        else PlayerMusic(it)
    })

    val player: LiveData<PlayerMusic> = _player

    private val _music = MutableLiveData<Music>(musicsFile.value?.let {
        if (it.isNotEmpty()) utils.setMusic(0, it[0].path)
        else Music(0, "", "", "", "", "", null)
    })
    val music: LiveData<Music> = _music

    private var _playlist = MutableLiveData<PlayListData>()
    val playlist: LiveData<PlayListData> = _playlist

    private var _reapt = MutableLiveData(false)
    var reapt: LiveData<Boolean> = _reapt

    private val _mute = MutableLiveData(false)
    val mute: LiveData<Boolean> = _mute

    private val _currentPosition = MutableLiveData(0)
    val currentPosition: LiveData<Int> = _currentPosition

    private val _progress = MutableLiveData(0f)
    val progress: LiveData<Float> = _progress

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _menu = MutableLiveData<Boolean>()
    val menu: LiveData<Boolean> = _menu

    init {
        _playlist.value = PlayListData("", "", "", "")
        val files = utils.getMusicFiles { }
        _musicsFile.value = files
        if (!_musicsFile.value.isNullOrEmpty()) {
            _player.value = PlayerMusic(_musicsFile.value!!)
        } else {
            _player.value =
                PlayerMusic(mutableListOf()) // Initialize with an empty list if no music files
        }
        _music.value =
            if (musicsFile.value?.size != 0) utils.setMusic(
                0,
                _musicsFile.value!![0].path
            ) else Music(
                0, "", "", "", "", "", null
            )

        _musics.value = musicsFile.value?.mapNotNull { file ->
            utils.setMusic(0, file.path)
        }?.toMutableList()
    }

    fun setCurrentPosition(num: Int) {
        _currentPosition.value = num
    }

    fun setMenu(boolean: Boolean) {
        _menu.value = boolean
    }

    fun setReapt(boolean: Boolean) {
        _reapt.value = boolean
    }

    fun setProgress(f: Float) {
        _progress.value = f
    }

    fun getFiles() {
        viewModelScope.launch {
            _musicsFile.value = utils.getMusicFiles { _musicsFile.value = mutableListOf() }
            _musics.value = musicsFile.value?.mapNotNull { file ->
                utils.setMusic(0, file.path)
            }?.toMutableList()
        }
    }

    // Função para atualizar o valor de pause
    fun setPause(boolean: Boolean) {
        viewModelScope.launch { _pause.value = boolean }
    }

    fun download(params: String, fileName: String, context: Context, salve: Boolean) {
        viewModelScope.launch {
            _loading.value = false
            try {
                val result = repository.download(params)
                if (salve) {
                    utils.createFile(fileName, result.bytes())
                    getFiles()
                } else {
                    player.value!!.audioCache(result.bytes(), title = _music.value!!.title, context)
                    _pause.value = true
                    if (player.value!!.isPlaying() && !reapt.value!!) startTime()
                }
                _musics.value = musicsFile.value?.mapNotNull { file ->
                    utils.setMusic(0, file.path)
                }?.toMutableList()
            } catch (e: Exception) {
                Log.d("EXC", "fetchYoutube: ${e.message.toString()}")
            } finally {
                this.cancel()
                _loading.value = true
            }
        }
    }

    fun setLoading(boolean: Boolean) {
        _loading.value = boolean
    }

    fun startTime() {
        viewModelScope.launch {
            while (true) {
                _currentPosition.value = player.value?.currentPosition()
                setProgress(
                    (_currentPosition.value!!.toFloat() / 1000f) / (player.value!!.duration()
                        .toFloat() / 1000f)
                )
                delay(1000) // atraso por um segundo
            }
        }
    }

    fun setMute(boolean: Boolean) {
        _mute.value = boolean
    }

    fun setMusicsFile(newMusics: MutableList<File>) {
        viewModelScope.launch { _musicsFile.value = newMusics }
    }

    fun setPlayer() {
        if (musicsFile.value?.isNotEmpty() == true) {
            _player.value = PlayerMusic(_musicsFile.value!!)
            _music.value = utils.setMusic(0, musicsFile.value!![0].path)
        }
    }

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
