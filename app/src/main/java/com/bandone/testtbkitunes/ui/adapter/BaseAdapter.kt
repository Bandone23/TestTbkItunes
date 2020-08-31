package com.bandone.testtbkitunes.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bandone.testtbkitunes.ui.adapter.holder.BaseViewHolder

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {
    protected val items = mutableListOf<T>()

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val item = items[position]

        holder.bindView(item)
    }

    abstract fun provideComparator(): Comparator<T>

    open fun swapItems(new: List<T>) {
        val diffUtil = GenericDiffUtil(old = items, new = new, comparator = provideComparator())

        DiffUtil.calculateDiff(diffUtil, true).dispatchUpdatesTo(this)

        items.clear()
        items.addAll(new)
    }

    open fun BaseViewHolder<T>.resolveItem() = if (adapterPosition != RecyclerView.NO_POSITION)
        items[adapterPosition]
    else
        null

    open fun updateItem(position: Int, update: T.() -> T) {
        items[position] = items[position].update()

        notifyItemChanged(position)
    }

    open fun updateItem(item: T, update: T.() -> T) {
        val position = items.indexOf(item)

        updateItem(position, update)
    }

    open fun getItem(position: Int): T {
        return items[position]
    }

    open fun removeItemAt(position: Int, notifyChange: Boolean = true) {
        items.removeAt(position)

        if (notifyChange)
            notifyItemRemoved(position)
    }

    open fun addItemAt(item: T, position: Int, notifyChange: Boolean = true) {
        items.add(position, item)

        if (notifyChange)
            notifyItemInserted(position)
    }

    open fun replaceItemAt(item: T, position: Int, notifyChange: Boolean = true) {
        items[position] = item

        if (notifyChange)
            notifyItemChanged(position)
    }

    private inner class GenericDiffUtil(
        private val old: List<T>,
        private val new: List<T>,
        private val comparator: Comparator<T>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = old.size

        override fun getNewListSize() = new.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = old[oldItemPosition]
            val newItem = new[newItemPosition]

            return comparator.compare(oldItem, newItem) == 0
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = old[oldItemPosition]
            val newItem = new[newItemPosition]

            return oldItem == newItem
        }
    }
}