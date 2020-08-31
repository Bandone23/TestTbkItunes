package com.bandone.testtbkitunes

import com.bandone.testtbkitunes.data.remote.api.ItunesRemoteApi
import com.bandone.testtbkitunes.util.extensions.getOrThrow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class ApiTest : KoinTest {

    private val artistIdQuery = 32940L
    private val albumIdQuery = 159292399L

    private val api: ItunesRemoteApi by inject()

    @Before
    fun `start koin`() {
        startKoin {
            modules(listOf(testModule))
        }
    }


    @Test
    fun `should get the artist's albums from iTunes api`() {
        val result = runBlocking {
            api.lookupAlbums(artistId = artistIdQuery).getOrThrow()
        }

        assertNotNull(result); result ?: return

        assertTrue(result.resultCount > 0)
    }

    @Test
    fun `should get the album's songs from iTunes api`() {
        val result = runBlocking {
            api.lookupTracks(albumId = albumIdQuery).getOrThrow()
        }

        assertNotNull(result); result ?: return

        assertTrue(result.resultCount > 0)
    }

    @After
    fun `stop koin`() {
        stopKoin()
    }
}
