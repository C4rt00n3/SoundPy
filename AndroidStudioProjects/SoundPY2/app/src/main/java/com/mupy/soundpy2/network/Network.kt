package com.mupy.soundpy2.network

import com.mupy.soundpy2.models.Musics
import com.mupy.soundpy2.models.PlayLists
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeService {
    @GET("search")
    suspend fun serach(
        @Query("query") query: String
    ): Musics

    @GET("playlist")
    suspend fun playlist(@Query("link") query: String): PlayLists

    @GET("download")
    suspend fun download(@Query("link") query: String): ResponseBody

    @GET
    suspend fun getThumb(): ResponseBody
}