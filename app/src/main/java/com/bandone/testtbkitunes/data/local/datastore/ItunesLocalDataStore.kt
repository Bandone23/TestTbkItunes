package com.bandone.testtbkitunes.data.local.datastore

import com.bandone.testtbkitunes.data.local.ItunesDatabase
import com.bandone.testtbkitunes.data.repository.ItunesDataStore
import com.bandone.testtbkitunes.domain.model.Album
import com.bandone.testtbkitunes.domain.model.Artist
import com.bandone.testtbkitunes.domain.model.Track
import com.bandone.testtbkitunes.util.extensions.normalize
import com.bandone.testtbkitunes.util.mappers.*

open class ItunesLocalDataStore(
private val itunesDatabase: ItunesDatabase
): ItunesDataStore {
    override suspend fun searchArtists(term: String): List<Artist> {
        return itunesDatabase.artists.searchArtists(term = term.normalize()).map { it.toArtist() }
    }

    override suspend fun getLikedArtists(): List<Artist> {
        return itunesDatabase.artists.getLikedArtists().map { it.toArtist() }
    }

    override suspend fun lookupAlbums(artistId: Long): List<Album> {
        return itunesDatabase.albums.lookupAlbums(artistId = artistId).map { it.toAlbum() }
    }

    override suspend fun lookupTracks(albumId: Long): List<Track> {
        return itunesDatabase.tracks.lookupTracks(albumId = albumId).map { it.toTrack() }
    }

    override suspend fun saveArtistsSearch(artists: List<Artist>, queryString: String) {
        val array = artists.map { it.toArtistEntity(queryString) }.toTypedArray()

        itunesDatabase.artists.saveArtistsSearch(artists = *array)
    }

    override suspend fun saveAlbumsLookup(albums: List<Album>) {
        val array = albums.map { it.toAlbumEntity() }.toTypedArray()

        itunesDatabase.albums.saveAlbumsLookup(albums = *array)
    }

    override suspend fun saveTracksLookup(tracks: List<Track>) {
        val array = tracks.map { it.toTrackEntity() }.toTypedArray()

        itunesDatabase.tracks.saveTracksLookup(tracks = *array)
    }

    override suspend fun likeArtist(artistId: Long) {
        itunesDatabase.artists.likeArtist(artistId = artistId)
    }

    override suspend fun unlikeArtist(artistId: Long) {
        itunesDatabase.artists.unlikeArtist(artistId = artistId)
    }
}