package com.mupy.music.utils

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import androidx.annotation.RequiresApi
import com.mupy.music.models.Music
import java.io.File
import java.io.FileDescriptor
import java.io.FileOutputStream

class PlayerMusic(private val mutableList: MutableList<File>) {
    private var mediaPlayer: MediaPlayer = MediaPlayer()
    private var count: Int = 0
    private val utils = Utils()
    private lateinit var playlist: List<Music>

    init {
        if (mutableList.isNotEmpty()) {
            mediaPlayer.setDataSource(mutableList[count].path)
            mediaPlayer.prepare()
        }
    }

    fun setPlaylist(p: List<Music>){
        playlist = p
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun audioCache(byteArray: ByteArray, title: String, context: Context) {
        try {
            // Criar um arquivo temporário com o ByteArray
            println("Criar um arquivo temporário com o ByteArray")
            val file = File.createTempFile(title, "mp4", context.cacheDir).apply {
                deleteOnExit()
                FileOutputStream(this).use { output ->
                    output.write(byteArray)
                }
            }
            reset()
            mediaPlayer = MediaPlayer()
            setDataSource(file)
            prepare()
            start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setDataSource(file: File): Unit = mediaPlayer.setDataSource(file.path)

    fun stop(): Unit = mediaPlayer.stop()

    @RequiresApi(Build.VERSION_CODES.P)
    fun start() {
        mediaPlayer.start()
    }

    fun pause() {
        mediaPlayer.pause()
    }

    fun seekTo(time: Int): Unit = mediaPlayer.seekTo(time)

    fun isPlaying(): Boolean {
        return mediaPlayer.isPlaying
    }

    fun timeUse(time: Int): String {
        val seconds = time / 1000f
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes.toInt(), remainingSeconds.toInt())
    }

    fun setDataSource(file: FileDescriptor): Unit = mediaPlayer.setDataSource(file)

    fun reset(): Unit = mediaPlayer.reset()

    fun prepare(): Unit = mediaPlayer.prepare()

    @RequiresApi(Build.VERSION_CODES.P)
    fun open(i: Int): Music? {
        if (i in 0 until mutableList.size) {
            count = i
            mediaPlayer.release()
            mediaPlayer = MediaPlayer()
            mediaPlayer.setDataSource(mutableList[count].path)
            mediaPlayer.prepare()
            mediaPlayer.start()
            return utils.setMusic(count, mutableList[count].path)
        } else {
            // Handle the case where the index is out of bounds.
            // You can log a message or throw an exception, depending on your requirements.
            return null
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun next(): Music? {
        println("+++++++++++++++++++++")
        if (mutableList.isNotEmpty()) {
            count = (count + 1) % mutableList.size
            mediaPlayer.release() // Libera recursos do MediaPlayer antes de recriá-lo
            mediaPlayer = MediaPlayer() // Cria uma nova instância do MediaPlayer
            mediaPlayer.setDataSource(mutableList[count].path)
            mediaPlayer.prepare()
            mediaPlayer.start()
            return utils.setMusic(count, mutableList[count].path)
        } else {
            // Lógica para lidar com a lista vazia, se necessário.
            // Pode retornar um objeto Music vazio ou lançar uma exceção, dependendo dos requisitos.
            return Music(
                0, "", "", "", "", "",null
            ) // Exemplo: retornando uma instância vazia de Music
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun back(): Music? {
        if (mutableList.isNotEmpty()) {
            count = (count - 1 + mutableList.size) % mutableList.size
            mediaPlayer.release() // Libera recursos do MediaPlayer antes de recriá-lo
            mediaPlayer = MediaPlayer() // Cria uma nova instância do MediaPlayer
            mediaPlayer.setDataSource(mutableList[count].path)
            mediaPlayer.prepare()
            mediaPlayer.start()
            return utils.setMusic(count, mutableList[count].path)
        } else {
            // Lógica para lidar com a lista vazia, se necessário.
            // Pode retornar um objeto Music vazio ou lançar uma exceção, dependendo dos requisitos.
            return Music(
                0, "", "", "", "", "",null
            ) // Exemplo: retornando uma instância vazia de Music
        }
    }

    fun duration(): Int = mediaPlayer.duration

    fun reapt() {
        mediaPlayer.setOnCompletionListener {
            mediaPlayer.seekTo(0)
            mediaPlayer.start()
        }
    }

    fun currentPosition(): Int = mediaPlayer.currentPosition

    fun mute(): Unit = mediaPlayer.setVolume(0f, 0f)

    fun setOnCompletionListener(call: () -> Unit): Unit =
        mediaPlayer.setOnCompletionListener { call() }

    fun unMute(): Unit =
        mediaPlayer.setVolume(1f, 1f) // Configura ambos os canais de áudio como 1 (volume total)
}