package com.mupy.soundpy2.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mupy.soundpy2.models.Musics
import com.mupy.soundpy2.models.PlayListData
import com.mupy.soundpy2.models.PlayLists
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

open class ApiViewModel : ViewModel() {
    var repository = ApiRepository()

    private val _searchYoutube = MutableLiveData<Musics>()
    val searchYoutube: LiveData<Musics> = _searchYoutube

    private val _playLists = MutableLiveData<MutableList<PlayLists>>(mutableListOf())
    val playLists: LiveData<MutableList<PlayLists>> = _playLists

    private val _playListData = MutableLiveData<PlayListData>()
    val playListData: LiveData<PlayListData> = _playListData

    fun fetchYoutube(params: String) {
        viewModelScope.launch {
            try {
                val result = repository.search(params)
                _searchYoutube.value = result
            } catch (e: Exception) {
                Log.d("EXC", "fetchYoutube: ${e.message.toString()}")
            } finally {
                this.cancel()
            }
        }
    }

    fun setPlaylisData(p: PlayListData) {
        _playListData.value = p
    }

    fun fetchPlaylist(params: String) {
        viewModelScope.launch {
            try {
                if (_playLists.value?.find { it.link == params } == null) {
                    val result = repository.playlist(params)
                    val newPlayLists = _playLists.value
                    newPlayLists?.add(result)
                    _playLists.value = mutableListOf()
                    _playLists.value = newPlayLists!!
                }
            } catch (e: Exception) {
                Log.d("EXC", "fetchYoutube: ${e.message.toString()}")
            } finally {
                this.cancel()
            }
        }
    }

    fun searchYoutube(params: String) {
        viewModelScope.launch {
            try {
                val result = repository.search(params)
                _searchYoutube.value = result
            } catch (e: Exception) {
                Log.d("EXC", "fetchYoutube: ${e.message.toString()}")
            } finally {
                this.cancel()
            }
        }
    }
}
