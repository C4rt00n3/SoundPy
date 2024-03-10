package com.mupy.soundpy.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mupy.soundpy.models.Musics
import com.mupy.soundpy.models.PlayListData
import com.mupy.soundpy.models.PlayLists
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

open class ApiViewModel : ViewModel() {
    open var repository = ApiRepository()

    private val _searchYoutube = MutableLiveData<Musics>()
    val searchYoutube: LiveData<Musics> = _searchYoutube

    private val _playlists = MutableLiveData<MutableList<PlayLists>>(mutableListOf())
    val playlists: LiveData<MutableList<PlayLists>> = _playlists

    private val _playListData = MutableLiveData<PlayListData>()
    val playListData: LiveData<PlayListData> = _playListData

    fun setPlaylisData(p: PlayListData) {
        _playListData.value = p
    }

    fun fetchPlaylist(params: String) {
        viewModelScope.launch {
            try {
                if (_playlists.value?.find { it.link == params } == null) {
                    val result = repository.playlist(params)
                    val newPlayLists = _playlists.value
                    newPlayLists?.add(result)
                    _playlists.value = mutableListOf()
                    _playlists.value = newPlayLists!!
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
