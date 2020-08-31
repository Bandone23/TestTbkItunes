package com.bandone.testtbkitunes.presentation.viewmodel

import com.bandone.testtbkitunes.domain.model.Artist
import com.bandone.testtbkitunes.domain.model.LikeArtistsRequest
import com.bandone.testtbkitunes.domain.model.SearchArtistsRequest
import com.bandone.testtbkitunes.domain.usecase.LikeArtistUseCase
import com.bandone.testtbkitunes.domain.usecase.SearchArtistsUseCase
import com.bandone.testtbkitunes.domain.usecase.UnlikeArtistUseCase
import com.bandone.testtbkitunes.presentation.state.SearchState
import com.bandone.testtbkitunes.util.extensions.LiveCompletable
import com.bandone.testtbkitunes.util.extensions.LiveResult
import com.bandone.testtbkitunes.util.extensions.execute
import com.bandone.testtbkitunes.util.extensions.postSuccess

open class SearchViewModel(
    private val searchArtistsUseCase: SearchArtistsUseCase,
    private val likeArtistUseCase: LikeArtistUseCase,
    private val unlikeArtistUseCase: UnlikeArtistUseCase
) : StateViewModel<SearchState>(initState = SearchState()) {
    val artists = LiveResult<List<Artist>>()
    val like = LiveCompletable()

    fun resetSearch() {
        artists.postSuccess(listOf())
    }

    fun searchArtists(term: String) = execute(
        useCase = searchArtistsUseCase,
        liveData = artists,
        params = SearchArtistsRequest(query = term)
    )

    fun likeArtist(artistId: Long) = execute(
        useCase = likeArtistUseCase,
        liveData = like,
        params = LikeArtistsRequest(artistId = artistId)
    )

    fun unlikeArtist(artistId: Long) = execute(
        useCase = unlikeArtistUseCase,
        liveData = like,
        params = LikeArtistsRequest(artistId = artistId)
    )
}