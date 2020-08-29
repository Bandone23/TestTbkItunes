package com.bandone.testtbkitunes.data.remote.api

import com.bandone.testtbkitunes.data.remote.model.AlbumEntry
import com.bandone.testtbkitunes.data.remote.model.ArtistEntry
import com.bandone.testtbkitunes.data.remote.model.SearchItunesResult
import com.bandone.testtbkitunes.data.remote.model.TrackEntry
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesRemoteApi {
    @GET("search")
    suspend fun searchArtists(
        @Query("term") terms: String,
        @Query("entity") entity: String = "musicArtist"
    ): Response<SearchItunesResult<ArtistEntry>>

    @GET("lookup")
    suspend fun lookupAlbums(
        @Query("id") artistId: Long,
        @Query("entity") entity: String = "album"
    ): Response<SearchItunesResult<AlbumEntry>>

    @GET("lookup")
    suspend fun lookupTracks(
        @Query("id") albumId: Long,
        @Query("entity") entity: String = "song"
    ): Response<SearchItunesResult<TrackEntry>>

}