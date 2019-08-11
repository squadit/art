package com.athena.art.adapter.recyclerview

import android.animation.Animator
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    protected var animationRunOnce = true

    protected fun runAnimation(holder: RecyclerView.ViewHolder) {
        getCustomAnimation()?.let {
            for (anim in it) {
                anim.setTarget(holder.itemView)
                anim.start()
            }
        } ?: run {
            startAnimation(holder.itemView)
        }
    }

    protected fun startAnimation(itemView: View) {
        val animator = RecyclerViewAnimationUtil.getAnimator(
            getAnimationType(),
            itemView
        )
        for (anim in animator) {
            anim.setDuration(800).start()
        }
    }

    protected open fun getAnimationType(): RecyclerViewAnimationUtil.AnimationType =
        RecyclerViewAnimationUtil.AnimationType.NONE

    protected open fun getCustomAnimation(): List<Animator>? = null
    protected fun animationRunOnce(animationRunOnce: Boolean) {
        this.animationRunOnce = animationRunOnce
    }
}