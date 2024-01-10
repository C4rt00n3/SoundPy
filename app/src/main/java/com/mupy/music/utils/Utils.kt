package com.mupy.music.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import com.mupy.music.models.Music
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class Utils {
    fun createFile(fileName: String, bytes: ByteArray): File {
        try {
            // Obtenha o diretório de música externo
            val musicDirectory =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)

            // Certifique-se de que o diretório existe ou crie-o
            if (!musicDirectory.exists()) {
                musicDirectory.mkdirs()
            }

            // Crie o arquivo no diretório especificado
            val file = File(musicDirectory, fileName.replace("-", "").replace("/", ""))

            // Use try-with-resources para garantir o fechamento adequado do FileOutputStream
            FileOutputStream(file).use { fos ->
                fos.write(bytes)
                println("Bytes salvos em: ${file.absolutePath}")
            }

            return file
        } catch (e: IOException) {
            println("Erro ao salvar os bytes no arquivo: ${e.message}")
            e.printStackTrace()
            throw e
        }
    }

    fun getMusicFiles(call: () -> Unit): MutableList<File> {
        call()

        val musicFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)

        return if (musicFolder.exists() && musicFolder.isDirectory) {
            val result = musicFolder.listFiles()?.filter { it.isFile }?.sortedByDescending { it.lastModified() }?.toMutableList()
                ?: mutableListOf()

            result
        } else {
            mutableListOf()
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun setMusic(id: Int, path: String): Music? {
        return try {
            val metadataRetriever = MediaMetadataRetriever()
            metadataRetriever.setDataSource(path)

            val title = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
            val artist =
                metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
            val album = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)

            val imageBytes = metadataRetriever.embeddedPicture
            val imageBitmap: Bitmap? = if (imageBytes != null) {
                BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            } else {
                null
            }

            Music(
                id.toLong(),
                artist.toString(),
                album.toString(),
                title.toString(),
                album.toString(),
                name = File(path).name,
                imageBitmap
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getFile(name: String, musics: MutableList<File>): File? {
        println(name)
        val result = musics.filter { name == it.name }

        if (result.isEmpty()) return null

        return result[0]
    }
}