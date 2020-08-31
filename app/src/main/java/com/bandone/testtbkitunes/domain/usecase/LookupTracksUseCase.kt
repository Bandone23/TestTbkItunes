package com.bandone.testtbkitunes.domain.usecase

import com.bandone.testtbkitunes.data.repository.RemoteRepository
import com.bandone.testtbkitunes.domain.model.LookupTracksRequest
import com.bandone.testtbkitunes.domain.model.Track
import com.bandone.testtbkitunes.util.coroutines.ResultUseCase
import kotlinx.coroutines.Dispatchers

open class LookupTracksUseCase(
    private val musicRepository: RemoteRepository
) : ResultUseCase<LookupTracksRequest, List<Track>>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: LookupTracksRequest): List<Track>? {
        return musicRepository.lookupTracks(albumId = params.albumId)
    }
}