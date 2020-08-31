package com.bandone.testtbkitunes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.bandone.testtbkitunes.domain.model.Artist
import com.bandone.testtbkitunes.domain.model.LikeArtistsRequest
import com.bandone.testtbkitunes.domain.usecase.GetLikedArtistsUseCase
import com.bandone.testtbkitunes.domain.usecase.UnlikeArtistUseCase
import com.bandone.testtbkitunes.util.extensions.LiveCompletable
import com.bandone.testtbkitunes.util.extensions.LiveResult
import com.bandone.testtbkitunes.util.extensions.execute


open class FavoritesViewModel(
    private val getLikedArtistsUseCase: GetLikedArtistsUseCase,
    private val unlikeArtistUseCase: UnlikeArtistUseCase
) : ViewModel() {
    val liked = LiveResult<List<Artist>>()
    val like = LiveCompletable()

    fun getLikedArtists() = execute(
        useCase = getLikedArtistsUseCase,
        liveData = liked,
        params = Unit
    )

    fun unlikeArtist(artistId: Long) = execute(
        useCase = unlikeArtistUseCase,
        liveData = like,
        params = LikeArtistsRequest(artistId = artistId)
    )
}