package com.mupy.soundpy.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MyPlaylists::class, Music::class], version = 5, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun service(): DaoMyPlaylists
}