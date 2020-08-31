package com.bandone.testtbkitunes

import android.app.Application
import com.bandone.testtbkitunes.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

const val URL_BASE_ITUNES_SEARCH_API = "https://itunes.apple.com/"
const val EXTRA_ARTIST_ID = "extraArtistId"
const val EXTRA_ALBUM_ID = "extraAlbumId"
const val EXTRA_URL = "extraUrl"
const val EXTRA_TITLE = "extraTitle"
const val SIZE_ALBUM_COVER = 130
const val TIME_SEARCHER_LOCKER = 300L

class TestTbkApp : Application()  {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TestTbkApp)
            androidFileProperties()
            modules(appModule)
        }
    }

}