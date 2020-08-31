package com.bandone.testtbkitunes.util.mappers

import com.bandone.testtbkitunes.data.local.model.AlbumEntity
import com.bandone.testtbkitunes.data.local.model.ArtistEntity
import com.bandone.testtbkitunes.data.local.model.TrackEntity
import com.bandone.testtbkitunes.domain.model.Album
import com.bandone.testtbkitunes.domain.model.Artist
import com.bandone.testtbkitunes.domain.model.Track

fun Artist.toArtistEntity(queryString: String) = ArtistEntity(
    artistId = artistId,
    artistName = artistName,
    artistLinkUrl = artistLinkUrl,
    primaryGenreName = primaryGenreName,
    primaryGenreId = primaryGenreId,
    like = if (isLiked) 1 else 0,
    queryString = queryString
)

fun Album.toAlbumEntity() = AlbumEntity(
    collectionId = collectionId,
    artistId = artistId,
    artistName = artistName,
    collectionName = collectionName,
    collectionCensoredName = collectionCensoredName,
    artistViewUrl = artistViewUrl,
    collectionViewUrl = collectionViewUrl,
    artworkUrl30 = artworkUrl30,
    artworkUrl60 = artworkUrl60,
    artworkUrl100 = artworkUrl100,
    collectionPrice = collectionPrice,
    isExplicit = if (isExplicit) 1 else 0,
    trackCount = trackCount,
    copyright = copyright,
    country = country,
    currency = currency,
    releaseDate = releaseDate.time,
    primaryGenreName = primaryGenreName
)

fun Track.toTrackEntity() = TrackEntity(
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
    isCollectionExplicit = if (isCollectionExplicit) 1 else 0,
    isTrackExplicit = if (isTrackExplicit) 1 else 0,
    discCount = discCount,
    discNumber = discNumber,
    trackCount = trackCount,
    trackNumber = trackNumber,
    trackTimeMillis = trackTimeMillis,
    country = country,
    currency = currency,
    primaryGenreName = primaryGenreName,
    isStreamable = if (isStreamable) 1 else 0,
    isDownloaded = if (isDownloaded) 1 else 0
)