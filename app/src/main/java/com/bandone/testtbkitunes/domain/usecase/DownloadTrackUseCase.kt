package com.bandone.testtbkitunes.domain.usecase

import com.bandone.testtbkitunes.data.local.ItunesDatabase
import com.bandone.testtbkitunes.data.repository.LocalRepository
import com.bandone.testtbkitunes.domain.model.DownloadTrackRequest
import com.bandone.testtbkitunes.domain.model.DownloadTrackResponse
import com.bandone.testtbkitunes.util.coroutines.ResultUseCase
import kotlinx.coroutines.Dispatchers
import java.io.File

open class DownloadTrackUseCase(
    private val storageRepository: LocalRepository,
    private val musicDatabase: ItunesDatabase
) : ResultUseCase<DownloadTrackRequest, DownloadTrackResponse>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: DownloadTrackRequest): DownloadTrackResponse? {
        val track = params.track

        val remoteFile = File(track.previewUrl)
        val fileName = "${track.trackId}.${remoteFile.extension}"
        val localFile = File("cache", fileName)

        /* If there is cache for this track */
        if (storageRepository.exists(fileName)) return DownloadTrackResponse(track, localFile)

        /* Download this track to cache */
        val downloadedFile = storageRepository.download(url = track.previewUrl, name = localFile.path)

        musicDatabase.tracks.markAsDownloaded(trackId = track.trackId)

        return DownloadTrackResponse(track, downloadedFile)
    }
}