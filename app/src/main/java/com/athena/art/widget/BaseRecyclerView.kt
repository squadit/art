package com.athena.art.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.athena.art.R

open class BaseRecyclerView : RecyclerView {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        if (layoutManager == null) layoutManager = LinearLayoutManager(context)
        setAttributes(attrs)
    }

    private fun setAttributes(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.RecyclerView, 0, 0)

        val spaceBetweenItems = typedArray.getDimension(R.styleable.BaseRecyclerView_it_space_between_items, 0f)
        val hasSpaceAfterLastItem =
            typedArray.getBoolean(R.styleable.BaseRecyclerView_it_has_space_after_last_item, true)
        setSpaceBetweenItems(spaceBetweenItems, hasSpaceAfterLastItem)

        val divider = typedArray.getDrawable(R.styleable.BaseRecyclerView_it_divider)
        val hasDividerAfterLastItem =
            typedArray.getBoolean(R.styleable.BaseRecyclerView_it_has_divider_after_last_item, false)
        setDivider(divider, hasDividerAfterLastItem)
    }

    private fun setDivider(divider: Drawable?, hasDividerAfterLastItem: Boolean) {
        divider?.let {
            val itemDecorator = object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
                    outRect.bottom = divider.intrinsicHeight
                }

                override fun onDraw(c: Canvas, parent: RecyclerView, state: State) {
                    val dividerLeft = parent.paddingLeft
                    val dividerRight = parent.width - parent.paddingRight

                    val iterationCount = if (hasDividerAfterLastItem) parent.childCount else parent.childCount - 1
                    for (i in 0 until iterationCount) {
                        val child = parent.getChildAt(i)

                        val params = child.layoutParams as LayoutParams

                        val dividerTop = child.bottom + params.bottomMargin
                        val dividerBottom = dividerTop + divider.intrinsicHeight

                        divider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
                        divider.draw(c)
                    }
                }
            }

            addItemDecoration(itemDecorator)
        }
    }

    private fun setSpaceBetweenItems(spaceBetweenItems: Float, hasSpaceAfterLastItem: Boolean) {
        if (spaceBetweenItems != 0f) {
            val itemDecorator = object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
                    if (hasSpaceAfterLastItem) {
                        outRect.bottom = spaceBetweenItems.toInt()
                    } else {
                        if (parent.getChildAdapterPosition(view) != parent.adapter?.itemCount!! - 1) {
                            outRect.bottom = spaceBetweenItems.toInt()
                        }
                    }
                }
            }
            addItemDecoration(itemDecorator)
        }
    }
}