package com.bandone.testtbkitunes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bandone.testtbkitunes.data.local.model.AlbumEntity

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albums WHERE artistId = :artistId")
    suspend fun lookupAlbums(artistId: Long): List<AlbumEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAlbumsLookup(vararg albums: AlbumEntity)
}
