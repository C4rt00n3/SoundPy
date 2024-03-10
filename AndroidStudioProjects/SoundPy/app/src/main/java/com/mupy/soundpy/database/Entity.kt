package com.mupy.soundpy.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.Date
import java.util.UUID


@Entity(tableName = "playlists")
data class MyPlaylists(
    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
    @ColumnInfo(name = "thumb") var thumb: ByteArray?,
    @ColumnInfo(name = "_name") val name: String?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MyPlaylists

        if (thumb != null) {
            if (other.thumb == null) return false
            if (!thumb.contentEquals(other.thumb)) return false
        } else if (other.thumb != null) return false

        return true
    }

    override fun hashCode(): Int {
        return thumb?.contentHashCode() ?: 0
    }
}

@Entity(
    tableName = "music", foreignKeys = [ForeignKey(
        entity = MyPlaylists::class,
        parentColumns = ["uid"],
        childColumns = ["playlistId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Music(
    @ColumnInfo(name = "playlistId") var playlistId: Int? = null,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "thumb") val thumb: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "bitImage") var bitImage: ByteArray?,
    @ColumnInfo(name = "directory") var directory: String,
    @ColumnInfo(name = "createdAt") val createAt: Long = Date().time,
    @PrimaryKey(autoGenerate = false) var id: String = UUID.randomUUID().toString()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Music

        if (author != other.author) return false
        if (bitImage != null) {
            if (other.bitImage == null) return false
            if (!bitImage.contentEquals(other.bitImage)) return false
        } else if (other.bitImage != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = author.hashCode()
        result = 31 * result + (bitImage?.contentHashCode() ?: 0)
        return result
    }
}

data class PlaylistWithMusic(
    @Embedded var playlist: MyPlaylists, @Relation(
        parentColumn = "uid", entityColumn = "playlistId"
    ) var music: MutableList<Music>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PlaylistWithMusic

        other.music.sortBy { it.createAt - Date().time }

        return true
    }
}