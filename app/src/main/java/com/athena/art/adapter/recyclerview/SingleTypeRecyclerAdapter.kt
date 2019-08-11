package com.athena.art.adapter.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class SingleTypeRecyclerAdapter<ItemType>(var items: MutableList<ItemType>) :
    BaseRecyclerAdapter<SingleTypeRecyclerViewHolder<ItemType>>() {

    private var lastAdapterPosition = -1
    private var itemSelectedListener: ((ItemType) -> Unit)? = null

    protected abstract fun getLayoutIdForItem(): Int
    protected abstract fun getViewHolder(view: View): SingleTypeRecyclerViewHolder<ItemType>

    fun setItemSelectedListener(listener: (ItemType) -> Unit) {
        itemSelectedListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleTypeRecyclerViewHolder<ItemType> {
        val view = LayoutInflater.from(parent.context).inflate(getLayoutIdForItem(), parent, false)
        return getViewHolder(view)
    }

    override fun onBindViewHolder(holder: SingleTypeRecyclerViewHolder<ItemType>, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { itemSelectedListener?.invoke(items[position]) }
        if (!animationRunOnce || holder.adapterPosition > lastAdapterPosition) {
            runAnimation(holder)
            lastAdapterPosition = holder.adapterPosition
        }
    }

    override fun getItemCount(): Int = items.size
}