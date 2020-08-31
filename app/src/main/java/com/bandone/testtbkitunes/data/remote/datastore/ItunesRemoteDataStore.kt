package com.bandone.testtbkitunes.data.remote.datastore

import com.bandone.testtbkitunes.data.remote.api.ItunesRemoteApi
import com.bandone.testtbkitunes.data.repository.ItunesDataStore
import com.bandone.testtbkitunes.domain.model.Album
import com.bandone.testtbkitunes.domain.model.Artist
import com.bandone.testtbkitunes.domain.model.Track
import com.bandone.testtbkitunes.util.extensions.getOrThrow
import com.bandone.testtbkitunes.util.mappers.toAlbum
import com.bandone.testtbkitunes.util.mappers.toArtist
import com.bandone.testtbkitunes.util.mappers.toTrack

open class ItunesRemoteDataStore(
    private val itunesRemoteApi: ItunesRemoteApi

):ItunesDataStore {
    override suspend fun searchArtists(term: String): List<Artist> {
        val response = itunesRemoteApi.searchArtists(term).getOrThrow()
        return response.results.map { it.toArtist() }
    }

    override suspend fun lookupAlbums(artistId: Long): List<Album> {
        val response = itunesRemoteApi.lookupAlbums(artistId).getOrThrow()

        return response.results.drop(1).map { it.toAlbum() }
    }

    override suspend fun lookupTracks(albumId: Long): List<Track> {
        val response = itunesRemoteApi.lookupTracks(albumId).getOrThrow()

        return response.results.drop(1).map { it.toTrack() }
    }

    override suspend fun getLikedArtists(): List<Artist> {
        throw NotImplementedError()
    }

    override suspend fun saveArtistsSearch(artists: List<Artist>, queryString: String) {
        throw NotImplementedError()
    }

    override suspend fun saveAlbumsLookup(albums: List<Album>) {
        throw NotImplementedError()
    }

    override suspend fun saveTracksLookup(tracks: List<Track>) {
        throw NotImplementedError()
    }

    override suspend fun likeArtist(artistId: Long) {
        throw NotImplementedError()
    }

    override suspend fun unlikeArtist(artistId: Long) {
        throw NotImplementedError()
    }
}