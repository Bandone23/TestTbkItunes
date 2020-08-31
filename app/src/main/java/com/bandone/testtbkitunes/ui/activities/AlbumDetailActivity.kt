package com.bandone.testtbkitunes.ui.activities

import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bandone.testtbkitunes.EXTRA_ALBUM_ID
import com.bandone.testtbkitunes.R
import com.bandone.testtbkitunes.domain.model.DownloadTrackResponse
import com.bandone.testtbkitunes.domain.model.Track
import com.bandone.testtbkitunes.presentation.model.TrackItem
import com.bandone.testtbkitunes.presentation.viewmodel.ArtistDetailViewModel
import com.bandone.testtbkitunes.ui.adapter.TrackAdapter
import com.bandone.testtbkitunes.util.MediaPlayerManager
import com.bandone.testtbkitunes.util.extensions.observe
import com.bandone.testtbkitunes.util.coroutines.Result
import com.bandone.testtbkitunes.util.extensions.isNetworkAvailable
import com.bandone.testtbkitunes.util.mappers.toTrack
import com.bandone.testtbkitunes.util.mappers.toTrackItem

import kotlinx.android.synthetic.main.activity_album_detail.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.support.v4.onRefresh
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumDetailActivity : AppCompatActivity() {

    private val viewModel: ArtistDetailViewModel by viewModel()

    private val connectionManager: ConnectivityManager by inject()

    private val trackAdapter = TrackAdapter(manager = TrackManager())

    private lateinit var mediaPlayerManager: MediaPlayerManager

    private val extraAlbumId by lazy {
        intent.getLongExtra(EXTRA_ALBUM_ID, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sRefreshAlbumDetail.isEnabled = false

        with(rViewAlbumDetail) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = trackAdapter
            setHasFixedSize(true)
        }

        with(viewModel) {
            observe(tracks, ::tracksObserver)
            observe(download, ::downloadObserver)

            lookupTracks(albumId = extraAlbumId)

            sRefreshAlbumDetail.onRefresh {
                lookupTracks(albumId = extraAlbumId)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return true
    }

    override fun onResume() {
        super.onResume()

        mediaPlayerManager = MediaPlayerManager(this)
    }

    override fun onPause() {
        mediaPlayerManager.release()

        trackAdapter.resetStates()

        super.onPause()
    }

    override fun onStop() {
        mediaPlayerManager.release()

        super.onStop()
    }

    private fun tracksObserver(result: Result<List<Track>>?) {
        when (result) {
            is Result.OnLoading -> {
                sRefreshAlbumDetail.isRefreshing = true
            }
            is Result.OnSuccess -> {
                sRefreshAlbumDetail.isRefreshing = false

                val tracks = result.value

                if (tracks.isNotEmpty())
                    supportActionBar?.title = tracks.first().collectionName

                val items = tracks.map(Track::toTrackItem)

                trackAdapter.swapItems(new = items)
            }
            is Result.OnError -> {
                sRefreshAlbumDetail.isRefreshing = false

                if (connectionManager.isNetworkAvailable())
                    longToast(R.string.toast_connection_failure)
                else
                    longToast(R.string.toast_no_connection)
            }
            else -> {
                sRefreshAlbumDetail.isRefreshing = false

                longToast(R.string.toast_unexpected_failure)
            }
        }
    }

    private fun downloadObserver(result: Result<DownloadTrackResponse>?) {
        when (result) {
            is Result.OnLoading -> {

            }
            is Result.OnSuccess -> {
                val response = result.value

                val isReady = !response.track.isDownloading || response.track.isDownloaded

                if (isReady) {
                    val track = response.track.let(Track::toTrackItem)

                    trackAdapter.updateItem(track) {
                        copy(
                            isPlaying = true,
                            isPaused = false,
                            isDownloading = false,
                            isDownloaded = true
                        )
                    }

                    mediaPlayerManager.play(source = response.file) {
                        trackAdapter.updateItem(track) {
                            copy(
                                isPlaying = false,
                                isPaused = false,
                                isDownloading = false
                            )
                        }
                    }
                }
            }
            is Result.OnError -> {
                if (connectionManager.isNetworkAvailable())
                    longToast(R.string.toast_connection_failure)
                else
                    longToast(R.string.toast_no_connection)
            }
        }
    }

    inner class TrackManager : TrackAdapter.AdapterManager {
        override fun onPlayTrackClicked(track: TrackItem, position: Int) {
            mediaPlayerManager.stop()

            if (track.isDownloaded)
                trackAdapter.updateItem(position = position) {
                    copy(
                        isPlaying = true,
                        isPaused = false,
                        isDownloading = false
                    )
                }
            else
                trackAdapter.updateItem(position = position) {
                    copy(
                        isPlaying = false,
                        isPaused = false,
                        isDownloading = true
                    )
                }

            viewModel.downloadTrack(track = track.let(TrackItem::toTrack))
        }

        override fun onPauseTrackClicked(track: TrackItem, position: Int) {
            mediaPlayerManager.pause()

            trackAdapter.updateItem(position = position) {
                copy(
                    isPlaying = false,
                    isPaused = true,
                    isDownloading = false
                )
            }
        }

    }
}
