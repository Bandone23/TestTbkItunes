package com.bandone.testtbkitunes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.bandone.testtbkitunes.domain.model.DownloadTrackRequest
import com.bandone.testtbkitunes.domain.model.DownloadTrackResponse
import com.bandone.testtbkitunes.domain.model.LookupTracksRequest
import com.bandone.testtbkitunes.domain.model.Track
import com.bandone.testtbkitunes.domain.usecase.LookupTracksUseCase
import com.bandone.testtbkitunes.util.extensions.LiveResult
import com.bandone.testtbkitunes.util.extensions.execute


import com.bandone.testtbkitunes.domain.usecase.DownloadTrackUseCase


open class ArtistDetailViewModel(
    private val lookupTracksUseCase: LookupTracksUseCase,
    private val downloadTrackUseCase: DownloadTrackUseCase
) : ViewModel() {
    val tracks = LiveResult<List<Track>>()
    val download = LiveResult<DownloadTrackResponse>()

    fun lookupTracks(albumId: Long) = execute(
        useCase = lookupTracksUseCase,
        liveData = tracks,
        params = LookupTracksRequest(albumId = albumId)
    )

    fun downloadTrack(track: Track) = execute(
        useCase = downloadTrackUseCase,
        liveData = download,
        params = DownloadTrackRequest(track = track)
    )
}