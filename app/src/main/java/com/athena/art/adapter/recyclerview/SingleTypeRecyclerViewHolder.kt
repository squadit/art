package com.athena.art.adapter.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class SingleTypeRecyclerViewHolder<ItemType>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: ItemType)
}