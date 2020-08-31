package com.bandone.testtbkitunes.domain.usecase

import com.bandone.testtbkitunes.data.repository.RemoteRepository
import com.bandone.testtbkitunes.domain.model.Artist
import com.bandone.testtbkitunes.domain.model.SearchArtistsRequest
import com.bandone.testtbkitunes.util.coroutines.ResultUseCase
import kotlinx.coroutines.Dispatchers

open class SearchArtistsUseCase(
    private val musicRepository: RemoteRepository
) : ResultUseCase<SearchArtistsRequest, List<Artist>>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: SearchArtistsRequest): List<Artist>? {
        return musicRepository.searchArtists(term = params.query)
    }
}