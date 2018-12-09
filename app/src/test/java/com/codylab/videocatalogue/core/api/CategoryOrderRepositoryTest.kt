package com.codylab.videocatalogue.core.api

import org.junit.Assert
import org.junit.Test

class CategoryOrderRepositoryTest {

    @Test
    fun categoryOrder() {
        // Given
        val categoryOrderRepository = CategoryOrderRepository()

        // When
        val featuresOrder = categoryOrderRepository.getCategoryOrder("Features")
        val moviesOrder = categoryOrderRepository.getCategoryOrder("Movies")
        val tVShowsOrder = categoryOrderRepository.getCategoryOrder("TV Shows")

        // Then
        Assert.assertTrue(featuresOrder < moviesOrder)
        Assert.assertTrue(moviesOrder < tVShowsOrder)
    }
}