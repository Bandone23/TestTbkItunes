package com.bandone.testtbkitunes.util.mappers

import com.bandone.testtbkitunes.data.local.model.AlbumEntity
import com.bandone.testtbkitunes.data.local.model.ArtistEntity
import com.bandone.testtbkitunes.data.local.model.TrackEntity
import com.bandone.testtbkitunes.data.remote.model.AlbumEntry
import com.bandone.testtbkitunes.data.remote.model.ArtistEntry
import com.bandone.testtbkitunes.data.remote.model.TrackEntry
import com.bandone.testtbkitunes.domain.model.Album
import com.bandone.testtbkitunes.domain.model.Artist
import com.bandone.testtbkitunes.domain.model.Track
import com.bandone.testtbkitunes.presentation.model.TrackItem
import com.bandone.testtbkitunes.util.extensions.parseISO8601Date
import java.util.*

fun ArtistEntry.toArtist() = Artist(
    artistId = artistId,
    artistName = artistName,
    artistLinkUrl = artistLinkUrl ?: "",
    primaryGenreName = primaryGenreName ?: "",
    primaryGenreId = primaryGenreId ?: 0,
    isLiked = false
)

fun AlbumEntry.toAlbum() = Album(
    collectionId = collectionId,
    artistId = artistId,
    artistName = artistName,
    collectionName = collectionName,
    collectionCensoredName = collectionCensoredName,
    artistViewUrl = artistViewUrl ?: "",
    collectionViewUrl = collectionViewUrl,
    artworkUrl30 = artworkUrl30 ?: "",
    artworkUrl60 = artworkUrl60 ?: "",
    artworkUrl100 = artworkUrl100 ?: "",
    collectionPrice = collectionPrice,
    isExplicit = collectionExplicitness != "notExplicit",
    trackCount = trackCount,
    copyright = copyright,
    country = country,
    currency = currency,
    releaseDate = releaseDate.parseISO8601Date(),
    primaryGenreName = primaryGenreName
)

fun TrackEntry.toTrack() = Track(
    artistId = artistId,
    collectionId = collectionId,
    trackId = trackId,
    artistName = artistName,
    collectionName = collectionName,
    trackName = trackName,
    collectionCensoredName = collectionCensoredName,
    trackCensoredName = trackCensoredName,
    artistViewUrl = artistViewUrl ?: "",
    collectionViewUrl = collectionViewUrl,
    trackViewUrl = trackViewUrl,
    previewUrl = previewUrl,
    collectionPrice = collectionPrice,
    trackPrice = trackPrice,
    releaseDate = releaseDate,
    isCollectionExplicit = collectionExplicitness != "notExplicit",
    isTrackExplicit = trackExplicitness != "notExplicit",
    discCount = discCount,
    discNumber = discNumber,
    trackCount = trackCount,
    trackNumber = trackNumber,
    trackTimeMillis = trackTimeMillis,
    country = country,
    currency = currency,
    primaryGenreName = primaryGenreName,
    isStreamable = isStreamable,
    isDownloading = false,
    isPlaying = false,
    isPaused = false,
    isDownloaded = false,
    isMusic = previewUrl.endsWith(".m4a"),
    isVideo = previewUrl.endsWith(".m4v")
)

fun ArtistEntity.toArtist() = Artist(
    artistId = artistId,
    artistName = artistName,
    artistLinkUrl = artistLinkUrl,
    primaryGenreName = primaryGenreName,
    primaryGenreId = primaryGenreId,
    isLiked = (like == 1)
)

fun AlbumEntity.toAlbum() = Album(
    collectionId = collectionId,
    artistId = artistId,
    artistName = artistName,
    collectionName = collectionName,
    collectionCensoredName = collectionCensoredName,
    artistViewUrl = artistViewUrl,
    collectionViewUrl = collectionViewUrl,
    artworkUrl30 = artworkUrl30 ?: "",
    artworkUrl60 = artworkUrl60 ?: "",
    artworkUrl100 = artworkUrl100 ?: "",
    collectionPrice = collectionPrice,
    isExplicit = (isExplicit == 1),
    trackCount = trackCount,
    copyright = copyright,
    country = country,
    currency = currency,
    releaseDate = Date(releaseDate),
    primaryGenreName = primaryGenreName
)

fun TrackEntity.toTrack() = Track(
    artistId = artistId,
    collectionId = collectionId,
    trackId = trackId,
    artistName = artistName,
    collectionName = collectionName,
    trackName = trackName,
    collectionCensoredName = collectionCensoredName,
    trackCensoredName = trackCensoredName,
    artistViewUrl = artistViewUrl,
    collectionViewUrl = collectionViewUrl,
    trackViewUrl = trackViewUrl,
    previewUrl = previewUrl,
    collectionPrice = collectionPrice,
    trackPrice = trackPrice,
    releaseDate = releaseDate,
    isCollectionExplicit = (isCollectionExplicit == 1),
    isTrackExplicit = (isTrackExplicit == 1),
    discCount = discCount,
    discNumber = discNumber,
    trackCount = trackCount,
    trackNumber = trackNumber,
    trackTimeMillis = trackTimeMillis,
    country = country,
    currency = currency,
    primaryGenreName = primaryGenreName,
    isStreamable = (isStreamable == 1),
    isDownloading = false,
    isPlaying = false,
    isPaused = false,
    isDownloaded = (isDownloaded == 1),
    isMusic = previewUrl.endsWith(".m4a"),
    isVideo = previewUrl.endsWith(".m4v")
)

fun TrackItem.toTrack() = Track(
    artistId = artistId,
    collectionId = collectionId,
    trackId = trackId,
    artistName = artistName,
    collectionName = collectionName,
    trackName = trackName,
    collectionCensoredName = collectionCensoredName,
    trackCensoredName = trackCensoredName,
    artistViewUrl = artistViewUrl,
    collectionViewUrl = collectionViewUrl,
    trackViewUrl = trackViewUrl,
    previewUrl = previewUrl,
    collectionPrice = collectionPrice,
    trackPrice = trackPrice,
    releaseDate = releaseDate,
    isCollectionExplicit = isCollectionExplicit,
    isTrackExplicit = isTrackExplicit,
    discCount = discCount,
    discNumber = discNumber,
    trackCount = trackCount,
    trackNumber = trackNumber,
    trackTimeMillis = trackTimeMillis,
    country = country,
    currency = currency,
    primaryGenreName = primaryGenreName,
    isStreamable = isStreamable,
    isDownloading = isDownloading,
    isPlaying = isPlaying,
    isPaused = isPaused,
    isDownloaded = isDownloaded,
    isMusic = isMusic,
    isVideo = isVideo
)