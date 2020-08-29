package com.bandone.testtbkitunes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bandone.testtbkitunes.data.local.database.TrackEntity

@Dao
interface TrackDao {
    @Query("SELECT * FROM tracks WHERE collectionId = :albumId")
    suspend fun lookupTracks(albumId: Long): List<TrackEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTracksLookup(vararg tracks: TrackEntity)

    @Query("UPDATE tracks SET isDownloaded = 1 WHERE trackId = :trackId")
    suspend fun markAsDownloaded(trackId: Long)
}
