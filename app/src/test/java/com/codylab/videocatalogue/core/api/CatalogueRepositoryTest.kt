package com.codylab.videocatalogue.core.api

import com.codylab.videocatalogue.TestUtils
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CatalogueRepositoryTest {

    @Mock
    lateinit var catalogueAPI: CatalogueAPI

    @Test
    fun getCategories() = runBlocking {
        // When
        val expectedCategories = TestUtils.getSampleCategories()
        val deferredCategories = async { expectedCategories }
        whenever(catalogueAPI.getVideos()).thenReturn(deferredCategories)
        val catalogueRepository = CatalogueRepository(catalogueAPI)

        // Given
        val actualCategories = catalogueRepository.getCategories()

        // Then
        assertEquals(expectedCategories, actualCategories)
    }
}