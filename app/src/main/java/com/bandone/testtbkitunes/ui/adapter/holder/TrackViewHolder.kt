package com.bandone.testtbkitunes.ui.adapter.holder

import android.view.View
import com.bandone.testtbkitunes.presentation.model.TrackItem
import com.bandone.testtbkitunes.util.extensions.drawables
import com.bandone.testtbkitunes.util.extensions.visible
import kotlinx.android.synthetic.main.item_track.view.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.textColorResource

open class TrackViewHolder(
    itemView: View
) : BaseViewHolder<TrackItem>(itemView) {
    override fun bindView(item: TrackItem) {
        with(itemView) {
            tViewTrackName.drawables(right = item.nameIconResource)

            val textColorResource = item.computeTextColorResource()
            val actionIconResource = item.computeActionIconResource()

            tViewTrackName.textColorResource = textColorResource
            tViewTrackTime.textColorResource = textColorResource
            iViewTrackAction.imageResource = actionIconResource

            tViewTrackName.text = item.trackName
            tViewTrackTime.text = item.timeMillisString

            pBarTrack.visible = item.isDownloading
            iViewTrackAction.visible = !item.isDownloading

            if (actionIconResource != 0) iViewTrackAction.imageResource = actionIconResource
        }
    }
}