package com.mupy.soundpy2

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mupy.soundpy2.models.Music
import com.mupy.soundpy2.models.PlayListData
import com.mupy.soundpy2.network.ApiViewModel
import com.mupy.soundpy2.utils.Play
import com.mupy.soundpy2.utils.Utils
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ContextMain : ApiViewModel() {
    private val utils = Utils()

    private val _searchInput = MutableLiveData("")
    val searchInput: LiveData<String> = _searchInput

    private val _musicsFile = MutableLiveData<MutableList<File>>(mutableListOf())
    val musicsFile: LiveData<MutableList<File>> = _musicsFile

    private val _count = MutableLiveData(0)
    val count: LiveData<Int> = _count

    private val _menu = MutableLiveData(false)
    val menu: LiveData<Boolean> = _menu

    private var limit = _musicsFile.value?.size ?: 0


    private val _player = MutableLiveData(Play())
    val player: LiveData<Play> = _player

    private val _musics = MutableLiveData<MutableList<Music>>(mutableListOf())
    val musics: LiveData<MutableList<Music>> = _musics

    private val _toast = MutableLiveData<String?>(null)
    val toast: LiveData<String?> = _toast

    private val _music = MutableLiveData<Music>(
        if (_musics.value?.isNotEmpty() == true) _musics.value!![0] else Music(
            0, "", "", "", "", "", null
        )
    )
    val music: LiveData<Music> = _music

    private val _pause = MutableLiveData(false)
    val pause: LiveData<Boolean> = _pause

    private val _reapt = MutableLiveData(false)
    val reapt: LiveData<Boolean> = _reapt

    private val _mute = MutableLiveData(false)
    val mute: LiveData<Boolean> = _mute

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _progress = MutableLiveData(0f)
    val progress: LiveData<Float> = _progress

    private val _currentPosition = MutableLiveData(0)
    val currentPosition: LiveData<Int> = _currentPosition

    fun setSearch(string: String) {
        _searchInput.value = string
    }

    @OptIn(ExperimentalFoundationApi::class)
    private val _pageState = MutableLiveData<PagerState?>(null)

    @OptIn(ExperimentalFoundationApi::class)
    val pageState: LiveData<PagerState?> = _pageState

    private val _playListsCards = MutableLiveData(
        listOf(
            PlayListData(
                "Sertanejo",
                "https://lh3.googleusercontent.com/18eW5KsqS0J0Q9pX3y6KqrfrsJB5-U_LMbEJ_s2SwGSeCm2Th_XMebciVMg8Upg372kTotCy8QSh6T4h=w544-h544-l90-rj",
                "RDCLAK5uy_k5faEHND0iXJljeASESqJ3A8UtRr2FL00",
                "Topnejo"
            ), PlayListData(
                "Trap",
                "https://yt3.googleusercontent.com/mhawOLp1YtaUXhAyQnvGIqNWP9oQ9Ry7QaVXEd_ymnTAC4eZ0pVHOIX0HD5ZZuW6mj1r--rFqWNQ=s576",
                "PLNyUJbuBiyw0SmnPYy4QfkDnpgq85fJBl",
                "TRAP BRASIL 2023"
            ), PlayListData(
                "Funk",
                "https://lh3.googleusercontent.com/Nbv9b6PTaELOo14LJO90khFgsXf62QStHsJtpaV1P0yLJwcskFCaONFLdaXGQYH7e5-7iMfhsx4tIQ=w544-h544-l90-rj",
                "RDCLAK5uy_nRxkdjoJYfKXKh4HyRtpuHKfmIfSH2khY",
                "Funk de Natal"
            ), PlayListData(
                "Rock",
                "https://lh3.googleusercontent.com/w8QDcpITg-64iylxia0Z4oWzbmlkHdSeSNyGslc_0ZcJgCtgLHkhugunsDRh_t87UQadn_si6-gPpvI=w544-h544-l90-rj",
                "RDCLAK5uy_nZiG9ehz_MQoWQxY5yElsLHCcG0tv9PRg",
                "Os maiores sucessos do rock clássico"
            ), PlayListData(
                "Pop",
                "https://lh3.googleusercontent.com/ih5QdpqpkVNRXtD-joBWj3jo1woxAXJFyAoA3hWYNWAKX0M9B825HH2VOh7aDX-unf67oyCyJGN9TljR=w544-h544-l90-rj",
                "RDCLAK5uy_nmS3YoxSwVVQk9lEQJ0UX4ZCjXsW_psU8",
                "Pop's Biggest Hits"
            )

        )
    )
    val playListsCards: LiveData<List<PlayListData>> = _playListsCards

    init {
        if (musics.value?.isNotEmpty() == true) musics.value?.first()?.let {
            setMusic(it)
        }
    }

    fun setMenu(boolean: Boolean) {
        _menu.value = boolean
    }

    @OptIn(ExperimentalFoundationApi::class)
    fun scrollToPage() {
        viewModelScope.launch {
            pageState.value?.scrollToPage(count.value ?: 0, 0.toFloat())
        }
    }

    fun setMute(b: Boolean) {
        _mute.value = b
    }

    fun setReapt(boolean: Boolean) {
        _reapt.value = boolean
    }

    fun setToast(toast: String?) {
        viewModelScope.launch {
            _toast.value = toast
            delay(5000)
            _toast.value = null
        }
    }

    fun mute() {
        player.value?.setVolume(0f, 0f)
    }

    @OptIn(ExperimentalFoundationApi::class)
    fun setPageState(p: PagerState) {
        _pageState.value = p
    }

    fun unMute() {
        player.value?.setVolume(1f, 1f)
    }

    fun setPlayer() {
        _player.value?.stop()
        _player.value = null
        _musicsFile.value?.toList()?.let {
            _player.value = Play()
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun open(position: Int) {
        viewModelScope.launch {
            if (_musicsFile.value?.isNotEmpty() == true) {
                try {
                    _count.value = position
                    play(true)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun next(): Music? {
        if (_musicsFile.value?.isNotEmpty() == true) {
            if (_count.value == limit) {
                _count.value = 0
            } else {
                _count.value = (_count.value!! + 1) % limit
            }
            play(true)
            player.value?.start()
            val result = utils.setMusic(_count.value!!, _musicsFile.value!![_count.value!!].path)
            result?.let {
                setMusic(it)
            }
            return result
        }
        return null
    }

    fun reapt() {
        player.value?.seekTo(0)
        player.value?.start()
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun back(): Music? {
        limit = musics.value?.size ?: 0
        _count.value = if(_count.value == 0) limit - 1 else (_count.value ?: 1) - 1
        play(true)
        return  utils.setMusic(_count.value!!, _musicsFile.value!![_count.value!!].path)?.let {
            setMusic(it)
            it
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun play(play: Boolean) {
        if (_musicsFile.value?.isNotEmpty() == true) {
            viewModelScope.launch {
                player.value?.reset()  // Reset before preparing again
                val path = _musicsFile.value!![_count.value!!].path
                player.value?.setDataSource(path)
                player.value?.prepare()
                if (play)
                    player.value?.start()
                setOnCompletionListener()
            }
            if (play)
                player.value?.apply {
                    isPlaying.let {
                        setPause(true)
                        startTime()
                    }
                }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun audioCache(byteArray: ByteArray, music: Music, title: String, context: Context) {
        try {
            // Creates temporary file
            val file = File.createTempFile(title, "mp3", context.cacheDir).apply {
                deleteOnExit()
            }

            // Write bytes to the file
            FileOutputStream(file).use { outputStream ->
                outputStream.write(byteArray)
            }

            // Initialize player
            _player.value?.apply {
                stop()
            }
            _player.value = Play().apply {
                setDataSource(file.path)
                prepare()
                start()
                setPause(isPlaying)
                startTime()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            setMusic(music)
            setToast("Concluido com sucesso!")
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @RequiresApi(Build.VERSION_CODES.P)
    fun setOnCompletionListener() {
        player.value!!.setOnCompletionListener {
            setProgress(0f)
            _currentPosition.value = 0
            if (_reapt.value == true) reapt()
            else viewModelScope.launch {
                next()
                pageState.value?.scrollToPage(count.value!!, 0f)
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    @RequiresApi(Build.VERSION_CODES.P)
    fun download(
        params: String, fileName: String, context: Context, save: Boolean, music: Music?
    ) {
        viewModelScope.launch {
            try {
                val result = repository.download(params)
                if (save) {
                    utils.createFile(
                        fileName, result.bytes(), context = context
                    )
                } else {
                    music?.let {
                        audioCache(
                            result.bytes(),
                            title = "${_music.value?.title}",
                            context = context,
                            music = it
                        )
                    }
                }

            } catch (e: IOException) {
                // Handle network-related errors
                e.printStackTrace()
                Log.e("NetworkException", "Error downloading: ${e.message}")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("UnknownException", "Error downloading: ${e.message}")
            } finally {
                if (save) {
                    getFiles(context)
                    _count.value = 0
                    play(false)
                    music?.let {
                        setMusic(it)
                    }
                    setToast("Baixado com sucesso!")
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun delet(file: File, context: Context) {
        try {
            file.delete()
        } catch (err: Exception) {
            err.printStackTrace()
        } finally {
            getFiles(context)
        }
    }

    fun setLoading(boolean: Boolean) {
        _loading.value = boolean
    }

    fun setPause(boolean: Boolean) {
        _pause.value = boolean
    }

    fun setMusic(m: Music) {
        _music.value = m
    }

    fun setProgress(f: Float) {
        _progress.value = f
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun startTime() {
        viewModelScope.launch {
            while (_pause.value == true) {
                _currentPosition.value = player.value?.currentPosition
                setProgress(
                    (_currentPosition.value!!.toFloat() / 1000f) / (player.value!!.duration.toFloat() / 1000f)
                )
                delay(1000) // atraso por um segundo
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun getFiles(context: Context) {
        viewModelScope.launch {
            _musicsFile.value = utils.getMusicFiles(context) { _musicsFile.value = mutableListOf() }
            _musics.value = musicsFile.value?.mapNotNull { file ->
                val tt = utils.setMusic(0, file.path)
                tt
            }?.toMutableList()
            if (_music.value?.title?.isBlank() == true && _musicsFile.value?.isNotEmpty() == true) {
                setMusic(_musics.value?.first()!!)
                play(false)
            }
        }
    }
}