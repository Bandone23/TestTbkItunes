package com.bandone.testtbkitunes.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.bandone.testtbkitunes.URL_BASE_ITUNES_SEARCH_API
import com.bandone.testtbkitunes.data.local.database.ItunesDatabase
import com.bandone.testtbkitunes.data.remote.api.ItunesRemoteApi
import com.bandone.testtbkitunes.util.DATABASE_NAME
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    /* Android Services */

    single {
        androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    /* Retrofit */

    single {
        OkHttpClient.Builder()
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(URL_BASE_ITUNES_SEARCH_API)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>()
            .create(ItunesRemoteApi::class.java) as ItunesRemoteApi
    }

    /* Database */

    single {
        Room.databaseBuilder(
            androidContext(),
            ItunesDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    /* Picasso */

    single {
        Picasso.get()
    }


}
