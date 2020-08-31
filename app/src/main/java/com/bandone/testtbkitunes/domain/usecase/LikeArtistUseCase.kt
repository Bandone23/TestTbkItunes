package com.bandone.testtbkitunes.domain.usecase

import com.bandone.testtbkitunes.data.repository.RemoteRepository
import com.bandone.testtbkitunes.domain.model.LikeArtistsRequest
import com.bandone.testtbkitunes.util.coroutines.CompletableUseCase
import kotlinx.coroutines.Dispatchers

open class LikeArtistUseCase(
    private val musicRepository: RemoteRepository
) : CompletableUseCase<LikeArtistsRequest>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: LikeArtistsRequest) {
        musicRepository.likeArtist(artistId = params.artistId)
    }
}