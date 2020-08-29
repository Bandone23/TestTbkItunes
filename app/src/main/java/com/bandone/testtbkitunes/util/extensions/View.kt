package com.bandone.testtbkitunes.util.extensions

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import kotlinx.coroutines.*

fun TextView.drawables(
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0
) = setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)

fun CheckBox.setSafeChecked(checked: Boolean) {
    val enabled = isEnabled
    isEnabled = false
    isChecked = checked
    isEnabled = enabled
}

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.INVISIBLE
    }

fun View.onClickOnce(onClick: () -> Unit) {
    setOnClickListener(object : View.OnClickListener {
        override fun onClick(view: View) {
            view.setOnClickListener(null)

            also { listener ->
                CoroutineScope(Dispatchers.Main).launch {
                    onClick()

                    withContext(Dispatchers.IO) { delay(500) }

                    view.setOnClickListener(listener)
                }
            }
        }
    })
}