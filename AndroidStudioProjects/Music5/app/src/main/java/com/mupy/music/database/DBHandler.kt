package com.mupy.music.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.mupy.music.models.Music

class DBHandler(context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE_COL + " TEXT,"
                + URL_COL + " TEXT,"
                + THUMB_COL + " TEXT,"
                + AUTHOR_COL + " TEXT)")
        db.execSQL(query)

        val playlistQuery = ("CREATE TABLE IF NOT EXISTS " + PLAYLIST_TABLE_NAME + " ("
                + PLAYLIST_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PLAYLIST_NAME_COL + " TEXT UNIQUE)")
        db.execSQL(playlistQuery)

        val musicQuery = ("CREATE TABLE IF NOT EXISTS " + PLAYLIST_TABLE_NAME + " ("
                + PLAYLIST_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PLAYLIST_NAME_COL + " TEXT)")
        db.execSQL(playlistQuery)

        val playlistMusicQuery = ("CREATE TABLE " + PLAYLIST_MUSIC_TABLE_NAME + " ("
                + PLAYLIST_ID_COL + " INTEGER,"
                + MUSIC_ID_COL + " INTEGER,"
                + "FOREIGN KEY(" + PLAYLIST_ID_COL + ") REFERENCES " + PLAYLIST_TABLE_NAME + "(" + PLAYLIST_ID_COL + "),"
                + "FOREIGN KEY(" + MUSIC_ID_COL + ") REFERENCES " + TABLE_NAME + "(" + ID_COL + "),"
                + "PRIMARY KEY (" + PLAYLIST_ID_COL + ", " + MUSIC_ID_COL + "))")
        db.execSQL(playlistMusicQuery)
    }

    fun addMusicToPlaylist(musicId: Long, playlistId: Long) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(PLAYLIST_ID_COL, playlistId)
        values.put(MUSIC_ID_COL, musicId)
        db.insert(PLAYLIST_MUSIC_TABLE_NAME, null, values)
        db.close()
    }

    fun addPlaylist(playlistName: String): Long {
        val db = this.writableDatabase

        // Verificar se a playlist já existe
        val existingPlaylistId = getPlaylistIdByName(playlistName)
        if (existingPlaylistId != -1L) {
            // Playlist já existe, retornar o ID existente
            db.close()
            return existingPlaylistId
        }

        // A playlist não existe, então podemos adicioná-la
        val values = ContentValues()
        values.put(PLAYLIST_NAME_COL, playlistName)
        val newPlaylistId = db.insert(PLAYLIST_TABLE_NAME, null, values)
        db.close()
        return newPlaylistId
    }

    fun getPlaylistIdByName(playlistName: String): Long {
        val db = this.writableDatabase

        val query = "SELECT $PLAYLIST_ID_COL FROM $PLAYLIST_TABLE_NAME WHERE $PLAYLIST_NAME_COL = ?"
        val cursor = db.rawQuery(query, arrayOf(playlistName))

        val playlistId: Long = if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(PLAYLIST_ID_COL)
            if (columnIndex >= 0) {
                cursor.getLong(columnIndex)
            } else {
                // Tratar caso a coluna não exista
                -1L
            }
        } else {
            -1L
        }

        cursor.close()
        return playlistId
    }

    fun addMusic(music: Music): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TITLE_COL, music.title)
        values.put(URL_COL, music.url)
        values.put(THUMB_COL, music.thumb)
        values.put(AUTHOR_COL, music.author)
        val musicId = db.insert(TABLE_NAME, null, values)
        db.close()
        return musicId
    }

    fun getAllMusicByPlaylistName(playlistName: String): List<Music> {
        val db = this.readableDatabase
        val musicList = mutableListOf<Music>()

        val playlistId = getPlaylistIdByName(playlistName)

        if (playlistId != -1L) {
            val query = "SELECT * FROM $TABLE_NAME INNER JOIN $PLAYLIST_MUSIC_TABLE_NAME ON $TABLE_NAME.$ID_COL = $PLAYLIST_MUSIC_TABLE_NAME.$MUSIC_ID_COL WHERE $PLAYLIST_MUSIC_TABLE_NAME.$PLAYLIST_ID_COL = ?"
            val cursor = db.rawQuery(query, arrayOf(playlistId.toString()))

            val idCol = cursor.getColumnIndex(ID_COL)
            val titleCol = cursor.getColumnIndex(TITLE_COL)
            val urlCol = cursor.getColumnIndex(URL_COL)
            val thumbCol = cursor.getColumnIndex(THUMB_COL)
            val authorCol = cursor.getColumnIndex(AUTHOR_COL)

            while (cursor.moveToNext()) {
                if (idCol >= 0) {
                    val id = cursor.getLong(idCol)
                    val title = cursor.getString(titleCol)
                    val url = cursor.getString(urlCol)
                    val thumb = cursor.getString(thumbCol)
                    val author = cursor.getString(authorCol)

                    val music = Music(id, title, url, thumb, author)
                    musicList.add(music)
                }
            }

            cursor.close()
        }

        db.close()
        return musicList
    }



    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $PLAYLIST_TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $PLAYLIST_MUSIC_TABLE_NAME")
        onCreate(db)
    }

    companion object {
        private const val PLAYLIST_TABLE_NAME = "playlist"
        private const val PLAYLIST_ID_COL = "playlist_id"
        private const val PLAYLIST_NAME_COL = "playlist_name"

        private const val PLAYLIST_MUSIC_TABLE_NAME = "playlist_music"
        private const val DB_NAME = "MuPy"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "music"
        const val ID_COL = "id"
        const val TITLE_COL = "title"
        const val URL_COL = "url"
        const val AUTHOR_COL = "author"
        const val THUMB_COL = "thumb"
        const val MUSIC_ID_COL = "music_id"
    }
}
