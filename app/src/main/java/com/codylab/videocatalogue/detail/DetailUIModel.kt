package com.codylab.videocatalogue.detail

import com.codylab.videocatalogue.core.livedata.Event
import com.codylab.videocatalogue.core.model.Item

data class DetailUIModel(
    val item: Item? = null,
    val closeEvent: Event<Unit>? = null
)