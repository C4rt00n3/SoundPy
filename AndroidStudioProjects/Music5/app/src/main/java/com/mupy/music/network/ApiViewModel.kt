package com.mupy.music.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mupy.music.models.Musics
import com.mupy.music.models.PlayListData
import com.mupy.music.models.PlayLists
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

open class ApiViewModel : ViewModel() {
    private var repository = ApiRepository()

    private val _searchYoutube = MutableLiveData<Musics>()
    val searchYoutube: LiveData<Musics> = _searchYoutube

    private val _musicArq  =  MutableLiveData<ResponseBody>()
    val musicFile: LiveData<ResponseBody> =  _musicArq


    private  val _find = MutableLiveData<PlayLists?>()
    val find: LiveData<PlayLists?> = _find

    private val  _playLists = MutableLiveData<MutableList<PlayLists>>(mutableListOf())
    val  playLists: LiveData<MutableList<PlayLists>> = _playLists


    private val _playListData  =  MutableLiveData<PlayListData>()
    val playListData: LiveData<PlayListData> =  _playListData

    fun fetchYoutube(params: String) {
        viewModelScope.launch {
            try {
                val result = repository.search(params)
                _searchYoutube.value = result
            } catch (e: Exception) {
                Log.d("EXC", "fetchYoutube: ${e.message.toString()}")
            }finally {
                this.cancel()
            }
        }
    }

    fun fetchPlaylist(params: String) {
        println("Iniciou")
        viewModelScope.launch {
            try {
                if(_playLists.value?.find { it.link == params } == null) {
                    val result = repository.playlist(params)
                    val newPlayLists = _playLists.value
                    newPlayLists?.add(PlayLists(params, result))
                    _playLists.value = mutableListOf()
                    _playLists.value = newPlayLists

                    println(playLists.value)
                    println(playLists.value?.size)
                }
            } catch (e: Exception) {
                Log.d("EXC", "fetchYoutube: ${e.message.toString()}")
            }finally {
                this.cancel()
            }
        }
    }

    fun addMusic(musics: Musics){
        _searchYoutube.value = musics
    }

    fun download(params: String) {

        viewModelScope.launch {
            try {
                val result = repository.download(params)
                 _musicArq.value  = result
            } catch (e: Exception) {
                Log.d("EXC", "fetchYoutube: ${e.message.toString()}")
            }finally {
                this.cancel()
            }
        }
    }
}
