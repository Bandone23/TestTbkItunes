package com.bandone.testtbkitunes.domain.usecase

import com.bandone.testtbkitunes.data.repository.RemoteRepository
import com.bandone.testtbkitunes.domain.model.Album
import com.bandone.testtbkitunes.domain.model.LookupAlbumsRequest
import com.bandone.testtbkitunes.util.coroutines.ResultUseCase
import kotlinx.coroutines.Dispatchers

open class LookupAlbumsUseCase(
    private val musicRepository: RemoteRepository
    ) : ResultUseCase<LookupAlbumsRequest, List<Album>>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: LookupAlbumsRequest): List<Album>? {
        return musicRepository.lookupAlbums(artistId = params.artistId)
    }
}