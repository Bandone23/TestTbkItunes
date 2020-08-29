package com.bandone.testtbkitunes.data.remote.datastore

import com.bandone.testtbkitunes.data.remote.api.ItunesRemoteApi
import com.bandone.testtbkitunes.data.repository.ItunesDataStore
import com.bandone.testtbkitunes.domain.model.Album
import com.bandone.testtbkitunes.domain.model.Artist
import com.bandone.testtbkitunes.domain.model.Track

open class ItunesRemoteDataStore(
    private val itunesRemoteApi: ItunesRemoteApi

):ItunesDataStore {
    override suspend fun searchArtists(term: String): List<Artist> {
        TODO("Not yet implemented")
    }

    override suspend fun getLikedArtists(): List<Artist> {
        TODO("Not yet implemented")
    }

    override suspend fun lookupAlbums(artistId: Long): List<Album> {
        TODO("Not yet implemented")
    }

    override suspend fun lookupTracks(albumId: Long): List<Track> {
        TODO("Not yet implemented")
    }

    override suspend fun saveArtistsSearch(artists: List<Artist>, queryString: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveAlbumsLookup(albums: List<Album>) {
        TODO("Not yet implemented")
    }

    override suspend fun saveTracksLookup(tracks: List<Track>) {
        TODO("Not yet implemented")
    }

    override suspend fun likeArtist(artistId: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun unlikeArtist(artistId: Long) {
        TODO("Not yet implemented")
    }
}