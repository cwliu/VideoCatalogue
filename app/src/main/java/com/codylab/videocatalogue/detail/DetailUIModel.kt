package com.codylab.videocatalogue.detail

import com.codylab.videocatalogue.core.livedata.Event
import com.codylab.videocatalogue.core.model.Item

data class DetailUIModel(
    var item: Item? = null,
    var closeEvent: Event<Unit>? = null
)