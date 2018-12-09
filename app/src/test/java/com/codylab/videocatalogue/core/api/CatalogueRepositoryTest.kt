package com.codylab.videocatalogue.core.api

import com.codylab.videocatalogue.core.coroutine.DispatcherManager
import com.codylab.videocatalogue.utils.TestDataUtils
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    @Mock
    lateinit var dispatcherManager: DispatcherManager

    @Mock
    lateinit var categoryOrderRepository: CategoryOrderRepository

    @ExperimentalCoroutinesApi
    @Test
    fun getCategories() = runBlocking {
        // When
        whenever(dispatcherManager.io).thenReturn(Dispatchers.Unconfined)
        val expectedCategories = TestDataUtils.getSampleCategories()
        val deferredCategories = async { expectedCategories }
        whenever(catalogueAPI.getCategories()).thenReturn(deferredCategories)
        val catalogueRepository = CatalogueRepository(
            catalogueAPI,
            dispatcherManager,
            categoryOrderRepository
        )

        // Given
        val actualCategories = catalogueRepository.getCategories()

        // Then
        assertEquals(expectedCategories, actualCategories)
    }
}