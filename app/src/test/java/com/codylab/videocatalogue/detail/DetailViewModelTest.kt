package com.codylab.videocatalogue.detail

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.codylab.videocatalogue.core.model.Images
import com.codylab.videocatalogue.core.model.Item
import com.codylab.videocatalogue.utils.testObserver
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun onLoad() {
        // Given
        val viewModel = DetailViewModel()
        val item = Item("a", Images("b", "c"), "d", 2000)
        val testObserver = viewModel.uiModelLiveData.testObserver()

        // When
        viewModel.onLoad(item)

        // Then
        val observedValues = testObserver.observedValues
        Assert.assertEquals( 1, observedValues.size)
        val loadedEvent = observedValues[0]!!
        Assert.assertNull(loadedEvent.closeEvent)
        Assert.assertEquals(item, loadedEvent.item)
    }

    @Test
    fun onClosedButtonClicked() {
        // Given
        val viewModel = DetailViewModel()
        val testObserver = viewModel.uiModelLiveData.testObserver()

        // When
        viewModel.onClosedButtonClicked()

        // Then
        val observedValues = testObserver.observedValues
        Assert.assertEquals( 1, observedValues.size)
        val closeEvent = observedValues[0]!!
        Assert.assertNotNull(closeEvent.closeEvent)
        Assert.assertNull(closeEvent.item)
    }
}