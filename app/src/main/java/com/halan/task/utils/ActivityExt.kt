package com.halan.task.utils

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

fun Activity.copy(word: String) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("tweet", word)
    clipboard.setPrimaryClip(clip)
}
