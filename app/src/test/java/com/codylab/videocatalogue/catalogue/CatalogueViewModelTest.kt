package com.codylab.videocatalogue.catalogue

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.codylab.videocatalogue.core.api.CatalogueRepository
import com.codylab.videocatalogue.core.coroutine.DispatcherManager
import com.codylab.videocatalogue.utils.TestDataUtils
import com.codylab.videocatalogue.utils.testObserver
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CatalogueViewModelTest {

    @Mock
    lateinit var catalogueRepository: CatalogueRepository

    @Mock
    lateinit var dispatchManager: DispatcherManager
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        whenever(dispatchManager.main).thenReturn(Dispatchers.Default)
    }

    @Test
    fun `Load data successfully`() = runBlocking {
        // Given
        val expectedCategories = TestDataUtils.getSampleCategories()
        val viewModel = CatalogueViewModel(catalogueRepository, dispatchManager)
        whenever(catalogueRepository.getCategories()).thenReturn(expectedCategories)
        val testObserver = viewModel.uiModelLiveData.testObserver()

        // When
        async { viewModel.onLoad() }.await()

        // Then
        val observedValues = testObserver.observedValues
        Assert.assertEquals( 3, observedValues.size)
        val onLoadingUiModel = observedValues[0]!!
        Assert.assertTrue(onLoadingUiModel.isLoading)

        val dataUiModel = observedValues[1]!!
        Assert.assertEquals(expectedCategories, dataUiModel.categories)

        val onCompletedUiModel = observedValues[2]!!
        Assert.assertFalse(onCompletedUiModel.isLoading)
    }
}