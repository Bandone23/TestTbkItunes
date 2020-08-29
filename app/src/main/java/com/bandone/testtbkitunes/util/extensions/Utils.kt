package com.bandone.testtbkitunes.util.extensions

inline fun <T> T?.notNull(exec: (T) -> Unit): T? = this?.also { exec(this) }