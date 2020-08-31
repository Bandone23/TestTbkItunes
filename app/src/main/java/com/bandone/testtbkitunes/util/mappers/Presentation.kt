package com.bandone.testtbkitunes.util.mappers

import com.bandone.testtbkitunes.R
import com.bandone.testtbkitunes.domain.model.Track
import com.bandone.testtbkitunes.presentation.model.TrackItem
import com.bandone.testtbkitunes.util.extensions.formatInterval

fun Track.toTrackItem() = TrackItem(
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
    isVideo = isVideo,
    /* Presentation fields */
    nameIconResource = if (isTrackExplicit) R.drawable.ic_explicit else 0,
    timeMillisString = trackTimeMillis.formatInterval()
)