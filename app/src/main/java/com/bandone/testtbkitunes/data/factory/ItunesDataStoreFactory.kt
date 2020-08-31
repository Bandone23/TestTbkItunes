package com.bandone.testtbkitunes.data.factory

import com.bandone.testtbkitunes.data.local.datastore.ItunesLocalDataStore
import com.bandone.testtbkitunes.data.remote.datastore.ItunesRemoteDataStore
import com.bandone.testtbkitunes.data.repository.ItunesDataStore

open class ItunesDataStoreFactory(
    private val localDataStore: ItunesLocalDataStore,
    private val remoteDataStore: ItunesRemoteDataStore
) {
    fun retrieveLocalDataStore(): ItunesDataStore = localDataStore
    fun retrieveRemoteDataStore(): ItunesDataStore = remoteDataStore
}