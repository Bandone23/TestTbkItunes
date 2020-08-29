package com.bandone.testtbkitunes.data.remote.model

data class SearchItunesResult <T>(
    val resultCount: Int,
    val results: List<T>
)