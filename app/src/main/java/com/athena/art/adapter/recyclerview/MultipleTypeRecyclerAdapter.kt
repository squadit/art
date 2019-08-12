package com.athena.art.adapter.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class MultipleTypeRecyclerAdapter(var items: MutableList<RecyclerViewViewType>) :
    BaseRecyclerAdapter<MultipleTypeRecyclerViewHolder>() {

    private var lastAdapterPosition = -1

    protected abstract fun getViewHolder(view: View, viewType: Int): MultipleTypeRecyclerViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultipleTypeRecyclerViewHolder {
        parentContext = parent.context
        val view = LayoutInflater.from(parentContext).inflate(viewType, parent, false)
        return getViewHolder(view, viewType)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return items[position].getLayoutId()
    }

    override fun onBindViewHolder(holder: MultipleTypeRecyclerViewHolder, position: Int) {
        holder.bind(items[position])
        if (!animationRunOnce || holder.adapterPosition > lastAdapterPosition) {
            runAnimation(holder)
            lastAdapterPosition = holder.adapterPosition
        }
    }
}