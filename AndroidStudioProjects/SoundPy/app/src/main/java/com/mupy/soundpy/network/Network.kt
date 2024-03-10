package com.mupy.soundpy.network

import com.mupy.soundpy.models.PlayLists
import com.mupy.soundpy.models.StreamYt
import com.mupy.soundpy.models.Musics
import com.mupy.soundpy.models.PlayListData
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeService {
    @GET("search")
    suspend fun serach(
        @Query("query") query: String
    ): Musics

    @GET("getPlaylist")
    suspend fun getPlaylists(
        @Query("query") query: String
    ): ArrayList<PlayListData>

    @GET("playlist")
    suspend fun playlist(@Query("link") query: String): PlayLists

    @GET("download")
    suspend fun download(@Query("link") query: String): ResponseBody

    @GET("stream")
    suspend fun stream(@Query("link") link: String): StreamYt

    @GET
    suspend fun getThumb(): ResponseBody
}