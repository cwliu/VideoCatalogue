package com.codylab.videocatalogue.core.api

import com.codylab.videocatalogue.utils.TestDataUtils
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
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
        val expectedCategories = TestDataUtils.getSampleCategories()
        val deferredCategories = async { expectedCategories }
        whenever(catalogueAPI.getCategories()).thenReturn(deferredCategories)
        val catalogueRepository = CatalogueRepository(catalogueAPI)

        // Given
        val actualCategories = catalogueRepository.getCategories()

        // Then
        assertEquals(expectedCategories, actualCategories)
    }
}