package com.bandone.testtbkitunes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bandone.testtbkitunes.R
import com.bandone.testtbkitunes.domain.model.Artist
import com.bandone.testtbkitunes.util.extensions.onClickOnce
import com.bandone.testtbkitunes.ui.adapter.holder.ArtistViewHolder
import com.bandone.testtbkitunes.ui.adapter.holder.BaseViewHolder
import kotlinx.android.synthetic.main.item_artist.view.*

open class ArtistAdapter(
    private val manager: AdapterManager
) : BaseAdapter<Artist>() {

    interface AdapterManager {
        fun onArtistClicked(item: Artist, position: Int)
        fun onArtistLikeChanged(item: Artist, position: Int, liked: Boolean)
    }

    override fun provideComparator() = compareBy(Artist::artistId)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Artist> {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_artist, parent, false)

        return ArtistViewHolder(itemView).also {
            with(itemView) {
                onClickOnce {
                    val item = it.resolveItem()

                    if (item != null)
                        manager.onArtistClicked(item, it.adapterPosition)
                }
            }
        }
    }

    fun setArtistLiked(liked: Boolean, position: Int) {
        items[position] = items[position].copy(isLiked = liked)

        notifyItemChanged(position)
    }

    fun removeLikedArtist(position: Int) {
        items.removeAt(position)

        notifyItemRemoved(position)
    }

    fun isEmpty() = items.isEmpty()
}