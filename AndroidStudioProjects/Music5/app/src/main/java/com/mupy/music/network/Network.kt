package com.mupy.music.network

import com.mupy.music.models.Musics
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeService {
    @GET("search")
    suspend fun serach(@Query("query") query: String): Musics

    @GET("playlist")
    suspend fun playlist(@Query("link") query: String): Musics

    @GET("download")
    suspend fun download(@Query("link") query: String): ResponseBody

    @GET
    suspend fun getThumb():ResponseBody
}