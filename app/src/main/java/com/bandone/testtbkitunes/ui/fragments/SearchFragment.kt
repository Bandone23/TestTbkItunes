package com.bandone.testtbkitunes.ui.fragments

import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.bandone.testtbkitunes.EXTRA_ARTIST_ID
import com.bandone.testtbkitunes.R
import com.bandone.testtbkitunes.TIME_SEARCHER_LOCKER
import com.bandone.testtbkitunes.domain.model.Artist
import com.bandone.testtbkitunes.presentation.state.SearchState
import com.bandone.testtbkitunes.presentation.viewmodel.SearchViewModel
import com.bandone.testtbkitunes.ui.activities.ArtistActivity
import com.bandone.testtbkitunes.ui.adapter.ArtistAdapter
import com.bandone.testtbkitunes.util.IdempotentLocker
import com.bandone.testtbkitunes.util.extensions.observe
import kotlinx.android.synthetic.main.fragment_search.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.textResource
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.android.ext.android.inject
import com.bandone.testtbkitunes.util.coroutines.Result
import com.bandone.testtbkitunes.util.extensions.drawables
import com.bandone.testtbkitunes.util.extensions.isNetworkAvailable
import org.jetbrains.anko.support.v4.longToast

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModel()

    private val connectionManager: ConnectivityManager by inject()
    private val artistAdapter = ArtistAdapter(manager = ArtistManager())

    private val locker = IdempotentLocker()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(rViewSearch) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = artistAdapter

            setHasFixedSize(true)
        }

        eTextSearch.doOnTextChanged { text, _, _, _ ->
            if (eTextSearch.isFocused) {
                locker.executeLast(TIME_SEARCHER_LOCKER) {
                    viewModel.setState {
                        copy(query = "$text")
                    }
                }
            }
        }

        with(viewModel) {
            observe(state, ::stateObserver)
            observe(artists, ::artistsObserver)
        }
    }

    private fun stateObserver(state: SearchState?) {
        state ?: return

        pBarSearch.visibility = View.VISIBLE

        val query = state.query

        if (query.isNotBlank())
            viewModel.searchArtists(term = query)
        else
            viewModel.resetSearch()
    }

    private fun artistsObserver(result: Result<List<Artist>>?) {
        when (result) {
            is Result.OnLoading -> {
                pBarSearch.visibility = View.VISIBLE
            }
            is Result.OnSuccess -> {
                pBarSearch.visibility = View.GONE

                val artists = result.value
                    .sortedBy { it.artistName }

                val isSearch = eTextSearch.text.toString().isNotBlank()

                if (artists.isNotEmpty()) {
                    tViewSearch.visibility = View.GONE
                    rViewSearch.visibility = View.VISIBLE
                } else {
                    if (isSearch) {
                        tViewSearch.textResource = R.string.text_search_no_results
                        tViewSearch.drawables(top = R.drawable.ic_search_empty)
                    } else {
                        tViewSearch.textResource = R.string.text_search_empty
                        tViewSearch.drawables(top = R.drawable.ic_search_empty)
                    }

                    rViewSearch.visibility = View.GONE
                    tViewSearch.visibility = View.VISIBLE
                }

                artistAdapter.swapItems(new = artists)
            }
            is Result.OnError -> {
                pBarSearch.visibility = View.GONE

                if (connectionManager.isNetworkAvailable())
                    longToast(R.string.toast_connection_failure)
                else
                    longToast(R.string.toast_no_connection)
            }
            else -> {
                pBarSearch.visibility = View.GONE

                longToast(R.string.toast_unexpected_failure)
            }
        }
    }

    inner class ArtistManager : ArtistAdapter.AdapterManager {
        override fun onArtistClicked(item: Artist, position: Int) {
           startActivity<ArtistActivity>(EXTRA_ARTIST_ID to item.artistId)
        }
        override fun onArtistLikeChanged(item: Artist, position: Int, liked: Boolean) {
            artistAdapter.setArtistLiked(liked, position)

            if (liked)
                viewModel.likeArtist(artistId = item.artistId)
            else
                viewModel.unlikeArtist(artistId = item.artistId)
        }
    }
}