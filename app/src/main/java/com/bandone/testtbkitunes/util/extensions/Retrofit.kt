package com.bandone.testtbkitunes.util.extensions

import retrofit2.HttpException
import retrofit2.Response

fun <T> Response<T>.getOrThrow() = body() ?: throw HttpException(this)