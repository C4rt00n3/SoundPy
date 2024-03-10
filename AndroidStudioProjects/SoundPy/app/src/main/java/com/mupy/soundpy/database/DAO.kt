package com.mupy.soundpy.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface DaoMyPlaylists {
    @Query("INSERT INTO playlists (thumb, _name) VALUES (:thumb, :name);")
    suspend fun createMyPlaylist(thumb: ByteArray?, name: String)

    @Insert(entity = Music::class)
    suspend fun createMusic(vararg music: Music)

    @Transaction
    @Query("SELECT EXISTS(SELECT 1 FROM music WHERE  url = :url AND playlistId = :playlistsId);")
    suspend fun getMusic(playlistsId: Int, url: String): Boolean

    @Transaction
    @Query("SELECT * FROM music ORDER BY createdAt DESC;")
    suspend fun gelAllMusics(): List<Music>

    @Query("SELECT EXISTS(SELECT 1 FROM music WHERE  url = :url );")
    suspend fun existMusic(url: String): Boolean

    @Update(onConflict = OnConflictStrategy.REPLACE, entity = MyPlaylists::class)
    suspend fun update(playlist: MyPlaylists)

    @Query("UPDATE music SET bitImage = :bitImage WHERE thumb = :thumb;")
    suspend fun updateMusic(bitImage: ByteArray?, thumb: String)

    @Query("DELETE FROM music WHERE url = :url AND playlistId = :playlistId;")
    suspend fun deleteMusic(url: String, playlistId: Int?)

    @Query("SELECT * FROM music WHERE thumb = :thumb;")
    suspend fun pickMusic(thumb: String): Music?

    @Delete
    suspend fun delete(playlists: MyPlaylists)

    // Example of a custom query
    @Transaction
    @Query("SELECT * FROM playlists WHERE uid = :uid;")
    suspend fun getPlaylistById(uid: Int): MyPlaylists

    @Transaction
    @Query("SELECT * FROM playlists;")
    suspend fun getAllPlaylistsWithMusic(): List<PlaylistWithMusic>

    @Transaction
    @Query("SELECT * FROM playlists WHERE uid = :uid;")
    suspend fun getOnePlaylist(uid: Int): PlaylistWithMusic
}

