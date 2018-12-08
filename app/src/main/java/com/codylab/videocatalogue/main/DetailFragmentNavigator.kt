package com.codylab.videocatalogue.main

import com.codylab.videocatalogue.core.model.Item

interface DetailFragmentNavigator {
    fun openDetailFragment(item: Item)
}
