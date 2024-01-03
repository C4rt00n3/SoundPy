package com.mupy.music.network

import com.mupy.music.models.Musics
import okhttp3.ResponseBody

class ApiRepository {
    private val api = Api.YouTube.service
    suspend fun search(query: String): Musics {
        return api.serach(query)
    }

    suspend fun playlist(link: String): Musics {
        return api.playlist(link)
    }

    suspend fun download(link: String): ResponseBody {
        return api.download(link)
    }
}