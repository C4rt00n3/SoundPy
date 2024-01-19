package com.mupy.soundpy2.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.mupy.soundpy2.models.Music
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.Locale

class Utils {
    fun createFile(fileName: String, bytes: ByteArray, context: Context): File {
        try {
            // Obtenha o diretório de arquivos internos específico do aplicativo
            val internalDir = context.filesDir

            // Crie um subdiretório se ainda não existir
            val musicDirectory = File(internalDir, "saved")
            if (!musicDirectory.exists()) {
                musicDirectory.mkdirs()
            }

            // Crie o arquivo no diretório de músicas internas
            val file = File(musicDirectory, fileName.replace("-", "").replace("/", "") + ".mp3")

            // Use try-with-resources para garantir o fechamento adequado do FileOutputStream
            FileOutputStream(file).use { fos ->
                fos.write(bytes)
            }
            Log.d("", "Bytes saved in: ${file.absolutePath}")
            return file
        } catch (e: IOException) {
            e.printStackTrace()
            throw e
        } finally {
            println("Criado...")
        }
    }

    fun getMusicFiles(context: Context, call: () -> Unit): MutableList<File> {
        return try {
            call()
            // Obtenha o diretório de arquivos internos específico do aplicativo
            val internalDir = context.filesDir
            // Crie um subdiretório se ainda não existir
            val musicDirectory = File(internalDir, "saved")
            if (!musicDirectory.exists()) {
                musicDirectory.mkdirs()
            }
            // Obtenha todos os arquivos no diretório de músicas internas
            val musicFiles = musicDirectory.listFiles { file ->
                file.isFile && file.extension.lowercase(Locale.ROOT) == "mp3"
            }?.sortedByDescending { it.lastModified() }?.toMutableList()
            // Converta a lista de arquivos para MutableList
            musicFiles?.toMutableList() ?: mutableListOf()
        } catch (e: SecurityException) {
            e.printStackTrace()
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

            val thumbnailUrlRegex = Regex("https://[^,\\s]+")

            val thumbnailMatch = thumbnailUrlRegex.find(album.toString())

            val thumbnailUrl = thumbnailMatch?.value?.trim()
            val url = album?.replace("thumbnail_url = $thumbnailUrl, url = ", "")

            val imageBytes = metadataRetriever.embeddedPicture
            val imageBitmap: Bitmap? = if (imageBytes != null) {
                BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            } else {
                null
            }

            Music(
                id = id.toLong(),
                author = artist.toString(),
                thumb = thumbnailUrl.toString(),
                title = title.toString(),
                url = url.toString(),
                name = File(path).name,
                bitImage = imageBitmap
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getFile(name: String, musics: MutableList<File>): File? {
        val result = musics.filter { name == it.name }

        if (result.isEmpty()) return null

        return result[0]
    }
}