package com.bandone.testtbkitunes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bandone.testtbkitunes.data.local.dao.AlbumDao
import com.bandone.testtbkitunes.data.local.dao.ArtistDao
import com.bandone.testtbkitunes.data.local.dao.TrackDao
import com.bandone.testtbkitunes.data.local.model.AlbumEntity
import com.bandone.testtbkitunes.data.local.model.ArtistEntity
import com.bandone.testtbkitunes.data.local.model.TrackEntity
import com.bandone.testtbkitunes.util.DATABASE_VERSION

@Database(
    entities = [
        ArtistEntity::class,
        AlbumEntity::class,
        TrackEntity::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class ItunesDatabase : RoomDatabase() {
    abstract val artists: ArtistDao
    abstract val albums: AlbumDao
    abstract val tracks: TrackDao
}