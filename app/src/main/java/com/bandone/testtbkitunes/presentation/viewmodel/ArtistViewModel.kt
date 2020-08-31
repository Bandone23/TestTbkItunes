package com.bandone.testtbkitunes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.bandone.testtbkitunes.domain.model.Album
import com.bandone.testtbkitunes.domain.model.LookupAlbumsRequest
import com.bandone.testtbkitunes.domain.usecase.LookupAlbumsUseCase
import com.bandone.testtbkitunes.util.extensions.LiveResult
import com.bandone.testtbkitunes.util.extensions.execute

open class ArtistViewModel(
    private val lookupAlbumsUseCase: LookupAlbumsUseCase
) : ViewModel() {
    val albums = LiveResult<List<Album>>()

    fun lookupAlbums(artistId: Long) = execute(
        useCase = lookupAlbumsUseCase,
        liveData = albums,
        params = LookupAlbumsRequest(artistId = artistId)
    )
}