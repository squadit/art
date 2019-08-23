package com.athena.art.adapter.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class MultipleTypeRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: Any, position: Int)
}