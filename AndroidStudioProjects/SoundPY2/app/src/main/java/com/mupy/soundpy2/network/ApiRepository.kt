package com.mupy.soundpy2.network

import com.mupy.soundpy2.models.Musics
import com.mupy.soundpy2.models.PlayLists
import okhttp3.ResponseBody

class ApiRepository {
    private val api = Api.YouTube.service

    suspend fun search(query: String): Musics {
        return api.serach(query)
    }

    suspend fun playlist(link: String): PlayLists {
        return api.playlist(link)
    }

    suspend fun download(link: String): ResponseBody {
        return api.download(link)
    }
}