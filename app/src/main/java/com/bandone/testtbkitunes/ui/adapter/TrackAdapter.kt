package com.bandone.testtbkitunes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bandone.testtbkitunes.R
import com.bandone.testtbkitunes.presentation.model.TrackItem
import com.bandone.testtbkitunes.util.extensions.onClickOnce
import com.bandone.testtbkitunes.ui.adapter.holder.BaseViewHolder
import com.bandone.testtbkitunes.ui.adapter.holder.TrackViewHolder

open class TrackAdapter(
    private val manager: AdapterManager
) : BaseAdapter<TrackItem>() {

    interface AdapterManager {
        fun onPlayTrackClicked(track: TrackItem, position: Int)
        fun onPauseTrackClicked(track: TrackItem, position: Int)
    }

    override fun provideComparator() = compareBy(TrackItem::trackId)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<TrackItem> {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false)

        return TrackViewHolder(itemView).also {
            with(itemView) {
                onClickOnce {
                    val item = it.resolveItem()

                    if (item != null)
                        when {
                            item.isMusic -> {
                                if (item.isPlaying)
                                    manager.onPauseTrackClicked(track = item, position = it.adapterPosition)
                                else
                                    manager.onPlayTrackClicked(track = item, position = it.adapterPosition)
                            }
                        }
                }
            }
        }
    }

    override fun updateItem(position: Int, update: TrackItem.() -> TrackItem) {
        resetStates()

        super.updateItem(position, update)
    }

    fun resetStates() {
        items.mapIndexedNotNull { index, track ->
            if (track.isPlaying || track.isPaused || track.isDownloading)
                index
            else
                null
        }.forEach { index ->
            items[index] = items[index].copy(
                isPlaying = false,
                isPaused = false,
                isDownloading = false
            )

            notifyItemChanged(index)
        }
    }
}