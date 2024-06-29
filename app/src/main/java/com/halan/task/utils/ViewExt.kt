package com.halan.task.utils

import android.view.View


/**
 * enable the view.
 */
fun View.enable(): View {
    this.isEnabled = true
    this.isClickable = true
    this.alpha = 1f
    return this
}

/**
 * disable the view.
 */
fun View.disable(): View {
    this.isEnabled = false
    this.isClickable = false
    this.alpha = 0.5f
    return this
}
