package com.bandone.testtbkitunes.ui.adapter.holder

import android.view.View
import com.bandone.testtbkitunes.R
import com.bandone.testtbkitunes.domain.model.Album
import com.bandone.testtbkitunes.ui.adapter.AlbumAdapter
import com.bandone.testtbkitunes.util.CircleTransform
import com.bandone.testtbkitunes.util.extensions.drawables
import com.bandone.testtbkitunes.util.extensions.formatYear
import kotlinx.android.synthetic.main.item_album.view.*
import org.jetbrains.anko.imageResource

open class AlbumViewHolder(
    itemView: View,
    private val manager: AlbumAdapter.AdapterManager
) : BaseViewHolder<Album>(itemView) {
    override fun bindView(item: Album) {
        with(itemView) {
            tViewAlbumName.text = item.collectionName
            tViewAlbumName.drawables(right = if (item.isExplicit) R.drawable.ic_explicit else 0)
            tViewAlbumArtistName.text = item.artistName
            tViewAlbumGenreAndYear.text = context.getString(
                R.string.text_album_genre_year,
                item.primaryGenreName,
                item.releaseDate.formatYear()
            )

            val artworkUrl = item.artworkUrl100

            if (artworkUrl.isNotBlank())
                with(receiver = manager.provideImageLoader()) {
                    load(artworkUrl)
                        .placeholder(R.mipmap.ic_launcher_round)
                        .transform(CircleTransform())
                        .into(iViewAlbumCover)
                }
            else
                iViewAlbumCover.imageResource = R.mipmap.ic_launcher_round
        }
    }
}