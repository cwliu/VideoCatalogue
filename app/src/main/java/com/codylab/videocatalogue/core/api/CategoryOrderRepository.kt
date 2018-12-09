package com.codylab.videocatalogue.core.api

import com.codylab.videocatalogue.core.model.Category
import javax.inject.Inject

class CategoryOrderRepository @Inject constructor() {

    fun getCategoryOrder(category: Category): Int{
        // Note:
        // The order of category in spec is not the same as in API response, so this repository
        // is to get the order of them, it's hardcoded for now, but the order should be
        // returned from API somehow.
        return when(category.category) {
            "Features" -> 1
            "Movies" -> 2
            "TV Shows" -> 3
            else -> {
                Int.MAX_VALUE
            }
        }
    }
}