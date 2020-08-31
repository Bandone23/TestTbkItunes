package com.bandone.testtbkitunes.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.bandone.testtbkitunes.URL_BASE_ITUNES_SEARCH_API
import com.bandone.testtbkitunes.data.factory.ItunesDataStoreFactory
import com.bandone.testtbkitunes.data.local.ItunesDatabase
import com.bandone.testtbkitunes.data.local.datastore.ItunesLocalDataStore
import com.bandone.testtbkitunes.data.remote.api.ItunesRemoteApi
import com.bandone.testtbkitunes.data.remote.datastore.ItunesRemoteDataStore
import com.bandone.testtbkitunes.data.repository.ItunesLocalRepository
import com.bandone.testtbkitunes.data.repository.ItunesRemoteRepository
import com.bandone.testtbkitunes.data.repository.LocalRepository
import com.bandone.testtbkitunes.data.repository.RemoteRepository
import com.bandone.testtbkitunes.domain.usecase.*
import com.bandone.testtbkitunes.presentation.viewmodel.ArtistDetailViewModel
import com.bandone.testtbkitunes.presentation.viewmodel.FavoritesViewModel
import com.bandone.testtbkitunes.presentation.viewmodel.SearchViewModel
import com.bandone.testtbkitunes.util.DATABASE_NAME
import com.bandone.testtbkitunes.domain.usecase.DownloadTrackUseCase
import com.bandone.testtbkitunes.presentation.viewmodel.ArtistViewModel
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module
import org.koin.experimental.builder.factory
import org.koin.experimental.builder.factoryBy
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


    /* Repositories */
    factoryBy<RemoteRepository, ItunesRemoteRepository>()
    factoryBy<LocalRepository, ItunesLocalRepository>()

    /*dataStore*/

    factory<ItunesRemoteDataStore>()
    factory<ItunesLocalDataStore>()


    /* Use cases */

    factory<DownloadTrackUseCase>()
    factory<LikeArtistUseCase>()
    factory<UnlikeArtistUseCase>()
    factory<LookupAlbumsUseCase>()
    factory<LookupTracksUseCase>()
    factory<SearchArtistsUseCase>()
    factory<GetLikedArtistsUseCase>()

    /* Factory */

    factory<ItunesDataStoreFactory>()

    /* View models */
    viewModel<SearchViewModel>()
    viewModel<FavoritesViewModel>()
    viewModel<ArtistViewModel>()
    viewModel<ArtistDetailViewModel>()



}
