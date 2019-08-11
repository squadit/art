package com.athena.art.adapter.recyclerview

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator

object RecyclerViewAnimationUtil {

    fun getAnimator(animationType: AnimationType, itemView: View): MutableList<Animator> {
        val animator: MutableList<Animator> = mutableListOf()
        when (animationType) {
            RecyclerViewAnimationUtil.AnimationType.NONE -> {
            }

            RecyclerViewAnimationUtil.AnimationType.FADE -> {
                val alpha = ObjectAnimator.ofFloat(itemView, "alpha", 0f, 1f)
                alpha.interpolator = DecelerateInterpolator()
                animator.add(alpha)
            }

            RecyclerViewAnimationUtil.AnimationType.FADE_AND_SLIDE_DOWN -> {
                val translationY = ObjectAnimator.ofFloat(itemView, "translationY", -itemView.height * .25f, 0f)
                translationY.interpolator = DecelerateInterpolator()
                val alpha = ObjectAnimator.ofFloat(itemView, "alpha", 0f, 1f)
                alpha.interpolator = DecelerateInterpolator()
                animator.add(translationY)
                animator.add(alpha)
            }

            RecyclerViewAnimationUtil.AnimationType.FADE_AND_SLIDE_TOP -> {
                val translationY = ObjectAnimator.ofFloat(itemView, "translationY", itemView.height * .25f, 0f)
                translationY.interpolator = DecelerateInterpolator()
                val alpha = ObjectAnimator.ofFloat(itemView, "alpha", 0f, 1f)
                alpha.interpolator = DecelerateInterpolator()
                animator.add(translationY)
                animator.add(alpha)
            }

            RecyclerViewAnimationUtil.AnimationType.FADE_AND_SLIDE_LEFT -> {
                val translationX = ObjectAnimator.ofFloat(itemView, "translationX", -itemView.width * .25f, 0f)
                translationX.interpolator = DecelerateInterpolator()
                val alpha = ObjectAnimator.ofFloat(itemView, "alpha", 0f, 1f)
                alpha.interpolator = DecelerateInterpolator()
                animator.add(translationX)
                animator.add(alpha)
            }

            RecyclerViewAnimationUtil.AnimationType.FADE_AND_SLIDE_RIGHT -> {
                val translationX = ObjectAnimator.ofFloat(itemView, "translationX", itemView.width * .25f, 0f)
                translationX.interpolator = DecelerateInterpolator()
                val alpha = ObjectAnimator.ofFloat(itemView, "alpha", 0f, 1f)
                alpha.interpolator = DecelerateInterpolator()
                animator.add(translationX)
                animator.add(alpha)
            }

            RecyclerViewAnimationUtil.AnimationType.SCALE_FROM_CENTER_TO_EDGE_HORIZONTALLY -> {
                val scaleX = ObjectAnimator.ofFloat(itemView, "scaleX", 0f, 1f)
                scaleX.interpolator = DecelerateInterpolator()
                animator.add(scaleX)
            }

            RecyclerViewAnimationUtil.AnimationType.SCALE_FROM_CENTER_TO_EDGE_VERTICALLY -> {
                val scaleY = ObjectAnimator.ofFloat(itemView, "scaleY", 0f, 1f)
                scaleY.interpolator = DecelerateInterpolator()
                animator.add(scaleY)
            }

            RecyclerViewAnimationUtil.AnimationType.SCALE_FROM_CENTER_TO_CORNER -> {
                val scaleX = ObjectAnimator.ofFloat(itemView, "scaleX", 0f, 1f)
                scaleX.interpolator = DecelerateInterpolator()
                val scaleY = ObjectAnimator.ofFloat(itemView, "scaleY", 0f, 1f)
                scaleY.interpolator = DecelerateInterpolator()
                animator.add(scaleX)
                animator.add(scaleY)
            }

            RecyclerViewAnimationUtil.AnimationType.SCALE_FROM_TOP_LEFT_TO_BOTTOM_RIGHT -> {
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

            RecyclerViewAnimationUtil.AnimationType.SCALE_FROM_BOTTOM_LEFT_TO_TOP_RIGHT -> {
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

            RecyclerViewAnimationUtil.AnimationType.SCALE_FROM_TOP_RIGHT_TO_BOTTOM_LEFT -> {
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

            RecyclerViewAnimationUtil.AnimationType.SCALE_FROM_BOTTOM_RIGHT_TO_TOP_LEFT -> {
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

            RecyclerViewAnimationUtil.AnimationType.LANDING -> {
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

            RecyclerViewAnimationUtil.AnimationType.SLIDE_FROM_LEFT_TO_RIGHT -> {
                val translationX =
                    ObjectAnimator.ofFloat(itemView, "translationX", -itemView.rootView.width.toFloat(), 0f)
                translationX.interpolator = DecelerateInterpolator()
                animator.add(translationX)
            }

            RecyclerViewAnimationUtil.AnimationType.SLIDE_FROM_LEFT_TO_RIGHT_OVERSHOOT -> {
                val translationX =
                    ObjectAnimator.ofFloat(itemView, "translationX", -itemView.rootView.width.toFloat(), 0f)
                translationX.interpolator = OvershootInterpolator(2f)
                animator.add(translationX)
            }

            RecyclerViewAnimationUtil.AnimationType.SLIDE_FROM_RIGHT_TO_LEFT -> {
                val translationX =
                    ObjectAnimator.ofFloat(itemView, "translationX", itemView.rootView.width.toFloat(), 0f)
                translationX.interpolator = DecelerateInterpolator()
                animator.add(translationX)
            }

            RecyclerViewAnimationUtil.AnimationType.SLIDE_FROM_RIGHT_TO_LEFT_OVERSHOOT -> {
                val translationX =
                    ObjectAnimator.ofFloat(itemView, "translationX", itemView.rootView.width.toFloat(), 0f)
                translationX.interpolator = OvershootInterpolator(2f)
                animator.add(translationX)
            }

            RecyclerViewAnimationUtil.AnimationType.SLIDE_FROM_BOTTOM_TO_TOP -> {
                val translationY =
                    ObjectAnimator.ofFloat(itemView, "translationY", itemView.measuredHeight.toFloat(), 0f)
                translationY.interpolator = DecelerateInterpolator()
                animator.add(translationY)
            }

            RecyclerViewAnimationUtil.AnimationType.SLIDE_FROM_BOTTOM_TO_TOP_OVERSHOOT -> {
                val translationY =
                    ObjectAnimator.ofFloat(itemView, "translationY", itemView.measuredHeight.toFloat(), 0f)
                translationY.interpolator = OvershootInterpolator(2f)
                animator.add(translationY)
            }
        }
        return animator
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