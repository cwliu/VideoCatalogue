package com.codylab.videocatalogue.catalogue

import com.codylab.videocatalogue.core.livedata.Event
import com.codylab.videocatalogue.core.model.Category

data class CatalogueUIModel (
    var categories: List<Category>? = null,
    var isLoading: Boolean = false,
    var hasError: Boolean = false,
    var message: Event<String>? = null
)
