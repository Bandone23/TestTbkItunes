package com.bandone.testtbkitunes.domain.usecase

import com.bandone.testtbkitunes.data.repository.RemoteRepository
import com.bandone.testtbkitunes.domain.model.Artist
import com.bandone.testtbkitunes.util.coroutines.ResultUseCase
import kotlinx.coroutines.Dispatchers

open class GetLikedArtistsUseCase(
    private val musicRepository: RemoteRepository
) : ResultUseCase<Unit, List<Artist>>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: Unit): List<Artist>? {
        return musicRepository.getLikedArtists()
    }
}