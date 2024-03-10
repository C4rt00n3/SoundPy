package com.mupy.soundpy.utils

import android.R
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import com.bumptech.glide.Glide
import com.mupy.soundpy.database.Music
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.Locale

class Utils {
    // Cria arquivo .mp3 em um diretório interno
    @RequiresApi(Build.VERSION_CODES.P)
    fun createFile(
        fileName: String, bytes: ByteArray, context: Context
    ): Music {
        try {
            // Obtenha o diretório de arquivos internos específico do aplicativo
            val internalDir = context.filesDir

            // Crie um subdiretório se ainda não existir
            val musicDirectory = File(internalDir, "saved")
            if (!musicDirectory.exists()) {
                musicDirectory.mkdirs()
            }

            // Crie o arquivo no diretório de músicas internas
            val cleanedFileName =
                fileName.replace(Regex("[^A-Za-z0-9 ]"), "").replace(" ", "") + ".mp3"
            val file = File(
                musicDirectory, "${cleanedFileName}.mp3"
            )

            FileOutputStream(file).use { fos ->
                fos.write(bytes)
            }
            Log.d("", "Bytes saved in: ${file.absolutePath}")
            return fileToMusic(file, context)
        } catch (e: IOException) {
            e.printStackTrace()
            throw e
        }
    }

    fun getImg(music: Music, context: Context, onLoad: ((Bitmap) -> Unit)?) {
        if (music.bitImage == null) {
            runBlocking { // Block the main thread temporarily
                val img = withContext(Dispatchers.IO) {
                    Glide.with(context).asBitmap().load(music.thumb)
                        .placeholder(R.drawable.progress_indeterminate_horizontal)
                        .error(R.drawable.stat_notify_error).submit().get()
                }
                onLoad?.let { it(img) }
            }
        }
    }

    // comprime um bitmap em um byteArry
    @TypeConverter
    fun compressBitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
        return stream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray?): Bitmap? {
        return byteArray?.let {
            BitmapFactory.decodeByteArray(it, 0, it.size)
        }
    }

    fun fileToMusic(file: File, context: Context): Music {
        val metadataRetriever = MediaMetadataRetriever()
        metadataRetriever.setDataSource(file.path)

        val title = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
        val artist = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
        val album = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)

        val picture = metadataRetriever.embeddedPicture

        val thumbnailUrlRegex = Regex("https://[^,\\s]+")

        val thumbnailMatch = thumbnailUrlRegex.find(album.toString())

        val thumbnailUrl = thumbnailMatch?.value?.trim()
        val url = album?.replace("thumbnail_url = $thumbnailUrl, url = ", "")

        val music = Music(
            author = artist.toString(),
            thumb = thumbnailUrl.toString(),
            title = title.toString(),
            url = url.toString(),
            name = file.name,
            directory = file.path,
            bitImage = picture,
            playlistId = 0
        )

        var byte = picture

        if (music.bitImage == null) getImg(music, context) {
            println("Bit image $it")
            byte = compressBitmapToByteArray(it)
        }
        return music.copy(bitImage = byte)
    }

    fun getMusicsSaved(context: Context): Array<Music> {
        return getMusicFiles(context) {}.map { fileToMusic(it, context) }.toTypedArray()
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

    fun deleteMusic(music: Music) {
        val file = File(music.directory)
        if (file.exists()) file.delete()
    }
}
