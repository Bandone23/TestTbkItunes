package com.bandone.testtbkitunes.ui.adapter.holder

import android.view.View
import com.bandone.testtbkitunes.domain.model.Artist
import com.bandone.testtbkitunes.util.extensions.setSafeChecked
import kotlinx.android.synthetic.main.item_artist.view.*

open class ArtistViewHolder(
    itemView: View
) :
    BaseViewHolder<Artist>(itemView) {
    override fun bindView(item: Artist) {
        with(itemView) {
            tViewArtistName.text = item.artistName
            tViewArtistGenre.text = item.primaryGenreName

        }
    }
}