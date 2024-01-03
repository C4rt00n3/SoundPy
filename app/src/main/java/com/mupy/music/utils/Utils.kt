package com.mupy.music.utils

import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.os.Environment
import com.mupy.music.models.Music
import com.mupy.music.models.Musics
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class Utils {
    fun createFile(fileName: String, bytes: ByteArray) {
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)

        val directory = File(path.path)

        if (!directory.exists()) {
            directory.mkdirs()
        }

        // Crie o arquivo no diretório especificado
        val file = File(directory, "$fileName.mp4")

        try {
            // Use FileOutputStream para escrever os bytes no arquivo
            val fos = FileOutputStream(file)
            fos.write(bytes)
            fos.close()

            println("Bytes salvos em: ${file.absolutePath}")
        } catch (e: IOException) {
            e.printStackTrace()
            println("Erro ao salvar os bytes no arquivo: ${e.message}")
        }
    }

    fun getMusicFiles(call: () -> Unit): MutableList<File> {
        call()

        val musicFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)

        val result = if (musicFolder.exists() && musicFolder.isDirectory) {
            musicFolder.listFiles()?.filter { it.isFile }?.map { it }?.toMutableList()
                ?: mutableListOf()
        } else {
            mutableListOf()
        }

        return if(result.size == 0) mutableListOf() else result
    }

    fun setMusic(id: Int, path: String): Music {
        val metadataRetriever = MediaMetadataRetriever()
        metadataRetriever.setDataSource(path)

        val title = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
        val artist = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
        val album = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
        return Music(
            id.toLong(), artist.toString(), album.toString(), title.toString(), album.toString()
        )
    }

    fun transformMusics(): Musics {
        val musicsResult: MutableList<Music> = mutableListOf()

        getMusicFiles {  }.map {
            val metadataRetriever = MediaMetadataRetriever()
            metadataRetriever.setDataSource(it.path)

            val title = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
            val artist = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
            val thumb = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)

            musicsResult.add(Music(musicsResult.size.toLong(), artist.toString(), thumb.toString(), title.toString(),thumb.toString() ))
        }

        return  Musics(musicsResult)
    }

    fun moveMusic(
        it: String,
        musics: MutableList<File>,
        music: Music,
        mediaPlayer: MediaPlayer,
        callMusic: (Music) -> Unit
    ) {
        val index = musics.indexOf(getFile(music.title, musics))
        mediaPlayer.stop()
        if (it == "next") {
            val position = if (index == musics.size - 1) 0 else index + 1
            callMusic(setMusic(position, musics[position].path))
            mediaPlayer.setDataSource(musics[position].path)
            mediaPlayer.prepare()
            mediaPlayer.start()
        } else {
            val position = if (index == 0) musics.size - 1 else index - 1
            callMusic(setMusic(position, musics[position].path))
            mediaPlayer.setDataSource(musics[position].path)
            mediaPlayer.prepare()
            mediaPlayer.start()
        }
    }

    fun getFile(name: String, musics: MutableList<File>): File {
        val result = musics.filter { "${name}.mp4" == it.name }

        return result[0]
    }
}