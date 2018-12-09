package com.codylab.videocatalogue.detail

import com.codylab.videocatalogue.core.model.Item

interface DetailFragmentNavigator {
    fun openDetailFragment(item: Item)
}
