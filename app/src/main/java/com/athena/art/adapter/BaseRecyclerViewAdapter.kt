package com.athena.art.adapter

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var animationRunOnce = true
    private var lastAdapterPosition = -1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (!animationRunOnce || holder.adapterPosition > lastAdapterPosition) {
            runAnimation(holder)
            lastAdapterPosition = holder.adapterPosition
        }
    }

    private fun runAnimation(holder: RecyclerView.ViewHolder) {
        getCustomAnimation()?.let {
            for (anim in it) {
                anim.setTarget(holder.itemView)
                anim.start()
            }
        } ?: run {
            startAnimation(holder.itemView)
        }
    }

    private fun startAnimation(itemView: View) {
        val animator: MutableList<Animator> = mutableListOf()
        when (getAnimationType()) {
            AnimationType.NONE -> {
            }

            AnimationType.FADE -> {
                val alpha = ObjectAnimator.ofFloat(itemView, "alpha", 0f, 1f)
                alpha.interpolator = DecelerateInterpolator()
                animator.add(alpha)
            }

            AnimationType.FADE_AND_SLIDE_DOWN -> {
                val translationY = ObjectAnimator.ofFloat(itemView, "translationY", -itemView.height * .25f, 0f)
                translationY.interpolator = DecelerateInterpolator()
                val alpha = ObjectAnimator.ofFloat(itemView, "alpha", 0f, 1f)
                alpha.interpolator = DecelerateInterpolator()
                animator.add(translationY)
                animator.add(alpha)
            }

            AnimationType.FADE_AND_SLIDE_TOP -> {
                val translationY = ObjectAnimator.ofFloat(itemView, "translationY", itemView.height * .25f, 0f)
                translationY.interpolator = DecelerateInterpolator()
                val alpha = ObjectAnimator.ofFloat(itemView, "alpha", 0f, 1f)
                alpha.interpolator = DecelerateInterpolator()
                animator.add(translationY)
                animator.add(alpha)
            }

            AnimationType.FADE_AND_SLIDE_LEFT -> {
                val translationX = ObjectAnimator.ofFloat(itemView, "translationX", -itemView.width * .25f, 0f)
                translationX.interpolator = DecelerateInterpolator()
                val alpha = ObjectAnimator.ofFloat(itemView, "alpha", 0f, 1f)
                alpha.interpolator = DecelerateInterpolator()
                animator.add(translationX)
                animator.add(alpha)
            }

            AnimationType.FADE_AND_SLIDE_RIGHT -> {
                val translationX = ObjectAnimator.ofFloat(itemView, "translationX", itemView.width * .25f, 0f)
                translationX.interpolator = DecelerateInterpolator()
                val alpha = ObjectAnimator.ofFloat(itemView, "alpha", 0f, 1f)
                alpha.interpolator = DecelerateInterpolator()
                animator.add(translationX)
                animator.add(alpha)
            }

            AnimationType.SCALE_FROM_CENTER_TO_EDGE_HORIZONTALLY -> {
                val scaleX = ObjectAnimator.ofFloat(itemView, "scaleX", 0f, 1f)
                scaleX.interpolator = DecelerateInterpolator()
                animator.add(scaleX)
            }

            AnimationType.SCALE_FROM_CENTER_TO_EDGE_VERTICALLY -> {
                val scaleY = ObjectAnimator.ofFloat(itemView, "scaleY", 0f, 1f)
                scaleY.interpolator = DecelerateInterpolator()
                animator.add(scaleY)
            }

            AnimationType.SCALE_FROM_CENTER_TO_CORNER -> {
                val scaleX = ObjectAnimator.ofFloat(itemView, "scaleX", 0f, 1f)
                scaleX.interpolator = DecelerateInterpolator()
                val scaleY = ObjectAnimator.ofFloat(itemView, "scaleY", 0f, 1f)
                scaleY.interpolator = DecelerateInterpolator()
                animator.add(scaleX)
                animator.add(scaleY)
            }

            AnimationType.SCALE_FROM_TOP_LEFT_TO_BOTTOM_RIGHT -> {
                val pivotX = ObjectAnimator.ofFloat(itemView, "pivotX", 0f)
                pivotX.interpolator = DecelerateInterpolator()
                val pivotY = ObjectAnimator.ofFloat(itemView, "pivotY", 0f)
                pivotY.interpolator = DecelerateInterpolator()
                val scaleX = ObjectAnimator.ofFloat(itemView, "scaleX", 0f, 1f)
                scaleX.interpolator = DecelerateInterpolator()
                val scaleY = ObjectAnimator.ofFloat(itemView, "scaleY", 0f, 1f)
                scaleY.interpolator = DecelerateInterpolator()
                animator.add(pivotX)
                animator.add(pivotY)
                animator.add(scaleX)
                animator.add(scaleY)
            }

            AnimationType.SCALE_FROM_BOTTOM_LEFT_TO_TOP_RIGHT -> {
                val pivotX = ObjectAnimator.ofFloat(itemView, "pivotX", 0f)
                pivotX.interpolator = DecelerateInterpolator()
                val pivotY = ObjectAnimator.ofFloat(itemView, "pivotY", itemView.height.toFloat())
                pivotY.interpolator = DecelerateInterpolator()
                val scaleX = ObjectAnimator.ofFloat(itemView, "scaleX", 0f, 1f)
                scaleX.interpolator = DecelerateInterpolator()
                val scaleY = ObjectAnimator.ofFloat(itemView, "scaleY", 0f, 1f)
                scaleY.interpolator = DecelerateInterpolator()
                animator.add(pivotX)
                animator.add(pivotY)
                animator.add(scaleX)
                animator.add(scaleY)
            }

            AnimationType.SCALE_FROM_TOP_RIGHT_TO_BOTTOM_LEFT -> {
                val pivotX = ObjectAnimator.ofFloat(itemView, "pivotX", itemView.width.toFloat())
                pivotX.interpolator = DecelerateInterpolator()
                val pivotY = ObjectAnimator.ofFloat(itemView, "pivotY", 0f)
                pivotY.interpolator = DecelerateInterpolator()
                val scaleX = ObjectAnimator.ofFloat(itemView, "scaleX", 0f, 1f)
                scaleX.interpolator = DecelerateInterpolator()
                val scaleY = ObjectAnimator.ofFloat(itemView, "scaleY", 0f, 1f)
                scaleY.interpolator = DecelerateInterpolator()
                animator.add(pivotX)
                animator.add(pivotY)
                animator.add(scaleX)
                animator.add(scaleY)
            }

            AnimationType.SCALE_FROM_BOTTOM_RIGHT_TO_TOP_LEFT -> {
                val pivotX = ObjectAnimator.ofFloat(itemView, "pivotX", itemView.width.toFloat())
                pivotX.interpolator = DecelerateInterpolator()
                val pivotY = ObjectAnimator.ofFloat(itemView, "pivotY", itemView.height.toFloat())
                pivotY.interpolator = DecelerateInterpolator()
                val scaleX = ObjectAnimator.ofFloat(itemView, "scaleX", 0f, 1f)
                scaleX.interpolator = DecelerateInterpolator()
                val scaleY = ObjectAnimator.ofFloat(itemView, "scaleY", 0f, 1f)
                scaleY.interpolator = DecelerateInterpolator()
                animator.add(pivotX)
                animator.add(pivotY)
                animator.add(scaleX)
                animator.add(scaleY)
            }

            AnimationType.LANDING -> {
                val alpha = ObjectAnimator.ofFloat(itemView, "alpha", 0f, 1f)
                alpha.interpolator = DecelerateInterpolator()
                val scaleX = ObjectAnimator.ofFloat(itemView, "scaleX", 0f, 1f)
                alpha.interpolator = DecelerateInterpolator()
                val scaleY = ObjectAnimator.ofFloat(itemView, "scaleY", 0f, 1f)
                alpha.interpolator = DecelerateInterpolator()
                animator.add(alpha)
                animator.add(scaleX)
                animator.add(scaleY)
            }

            AnimationType.SLIDE_FROM_LEFT_TO_RIGHT -> {
                val translationX =
                    ObjectAnimator.ofFloat(itemView, "translationX", -itemView.rootView.width.toFloat(), 0f)
                translationX.interpolator = DecelerateInterpolator()
                animator.add(translationX)
            }

            AnimationType.SLIDE_FROM_LEFT_TO_RIGHT_OVERSHOOT -> {
                val translationX =
                    ObjectAnimator.ofFloat(itemView, "translationX", -itemView.rootView.width.toFloat(), 0f)
                translationX.interpolator = OvershootInterpolator(2f)
                animator.add(translationX)
            }

            AnimationType.SLIDE_FROM_RIGHT_TO_LEFT -> {
                val translationX =
                    ObjectAnimator.ofFloat(itemView, "translationX", itemView.rootView.width.toFloat(), 0f)
                translationX.interpolator = DecelerateInterpolator()
                animator.add(translationX)
            }

            AnimationType.SLIDE_FROM_RIGHT_TO_LEFT_OVERSHOOT -> {
                val translationX =
                    ObjectAnimator.ofFloat(itemView, "translationX", itemView.rootView.width.toFloat(), 0f)
                translationX.interpolator = OvershootInterpolator(2f)
                animator.add(translationX)
            }

            AnimationType.SLIDE_FROM_BOTTOM_TO_TOP -> {
                val translationY =
                    ObjectAnimator.ofFloat(itemView, "translationY", itemView.measuredHeight.toFloat(), 0f)
                translationY.interpolator = DecelerateInterpolator()
                animator.add(translationY)
            }

            AnimationType.SLIDE_FROM_BOTTOM_TO_TOP_OVERSHOOT -> {
                val translationY =
                    ObjectAnimator.ofFloat(itemView, "translationY", itemView.measuredHeight.toFloat(), 0f)
                translationY.interpolator = OvershootInterpolator(2f)
                animator.add(translationY)
            }
        }

        for (anim in animator) {
            anim.setDuration(800).start()
        }
    }

    protected open fun getAnimationType(): AnimationType = AnimationType.NONE
    protected open fun getCustomAnimation(): List<Animator>? = null
    protected fun animationRunOnce(animationRunOnce: Boolean) {
        this.animationRunOnce = animationRunOnce
    }

    enum class AnimationType {
        NONE,
        FADE, FADE_AND_SLIDE_DOWN, FADE_AND_SLIDE_TOP, FADE_AND_SLIDE_LEFT, FADE_AND_SLIDE_RIGHT,
        SCALE_FROM_CENTER_TO_EDGE_HORIZONTALLY, SCALE_FROM_CENTER_TO_EDGE_VERTICALLY, SCALE_FROM_CENTER_TO_CORNER,
        SCALE_FROM_TOP_LEFT_TO_BOTTOM_RIGHT, SCALE_FROM_BOTTOM_LEFT_TO_TOP_RIGHT, SCALE_FROM_TOP_RIGHT_TO_BOTTOM_LEFT,
        SCALE_FROM_BOTTOM_RIGHT_TO_TOP_LEFT, LANDING,
        SLIDE_FROM_LEFT_TO_RIGHT, SLIDE_FROM_RIGHT_TO_LEFT, SLIDE_FROM_BOTTOM_TO_TOP,
        SLIDE_FROM_LEFT_TO_RIGHT_OVERSHOOT, SLIDE_FROM_RIGHT_TO_LEFT_OVERSHOOT, SLIDE_FROM_BOTTOM_TO_TOP_OVERSHOOT
    }
}