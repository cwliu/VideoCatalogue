package com.codylab.videocatalogue.catalogue

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.codylab.videocatalogue.core.api.CatalogueRepository
import com.codylab.videocatalogue.core.coroutine.DispatcherManager
import com.codylab.videocatalogue.utils.TestDataUtils
import com.codylab.videocatalogue.utils.TestObserver
import com.codylab.videocatalogue.utils.testObserver
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var catalogueRepository: CatalogueRepository

    @Mock
    lateinit var dispatchManager: DispatcherManager

    private lateinit var viewModel: CatalogueViewModel

    private lateinit var testObserver: TestObserver<CatalogueUIModel>

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        whenever(dispatchManager.main).thenReturn(Dispatchers.Unconfined)
        viewModel = CatalogueViewModel(dispatchManager, catalogueRepository)
        testObserver = viewModel.uiModelLiveData.testObserver()
    }

    @Test
    fun loadDataSuccessfully() = runBlocking {
        // Given
        val expectedCategories = TestDataUtils.getSampleCategories()
        val viewModel = CatalogueViewModel(dispatchManager, catalogueRepository)
        val testObserver = viewModel.uiModelLiveData.testObserver()
        whenever(catalogueRepository.getCategories()).thenReturn(expectedCategories)

        // When
        async { viewModel.onLoad() }.await()

        // Then
        val observedValues = testObserver.observedValues
        Assert.assertEquals(3, observedValues.size)
        val onLoadingUiModel = observedValues[0]!!
        Assert.assertTrue(onLoadingUiModel.isLoading)

        val dataUiModel = observedValues[1]!!
        Assert.assertEquals(expectedCategories, dataUiModel.categories)

        val onCompletedUiModel = observedValues[2]!!
        Assert.assertFalse(onCompletedUiModel.isLoading)
    }

    @Test
    fun loadDataFailed() = runBlocking {
        // Given
        whenever(catalogueRepository.getCategories()).thenThrow(IllegalStateException("Drill"))

        // When
        async { viewModel.onLoad() }.await()

        // Then
        val observedValues = testObserver.observedValues
        Assert.assertEquals(3, observedValues.size)
        val onLoadingUiModel = observedValues[0]!!
        Assert.assertTrue(onLoadingUiModel.isLoading)

        val dataUiModel = observedValues[1]!!
        Assert.assertEquals(null, dataUiModel.categories)
        Assert.assertEquals(true, dataUiModel.hasError)

        val onCompletedUiModel = observedValues[2]!!
        Assert.assertFalse(onCompletedUiModel.isLoading)
    }
}