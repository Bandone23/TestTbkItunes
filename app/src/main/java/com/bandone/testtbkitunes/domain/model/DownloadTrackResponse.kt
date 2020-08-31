package com.bandone.testtbkitunes.domain.model

import java.io.File

data class DownloadTrackResponse(
    val track: Track,
    val file: File
)