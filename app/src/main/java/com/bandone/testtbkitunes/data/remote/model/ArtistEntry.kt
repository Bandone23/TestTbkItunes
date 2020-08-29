package com.bandone.testtbkitunes.data.remote.model

data class ArtistEntry(
    val artistId: Long,
    val artistName: String,
    val artistLinkUrl: String?,
    val primaryGenreName: String?,
    val primaryGenreId: Long?
)